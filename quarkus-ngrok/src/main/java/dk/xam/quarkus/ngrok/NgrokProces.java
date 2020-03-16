package dk.xam.quarkus.ngrok;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@ApplicationScoped
public class NgrokProces {

    private static final String bin = "ngrok" +
                                (System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "");

    private static final Logger log = LoggerFactory.getLogger(NgrokProces.class);
    private Process ngrok_process;
    private ExecutorService executor;
    private String lastAddress;

    @Inject
    Event<NgrokReadyEvent> connectEvent;

    void start(@Observes StartupEvent ev) throws IOException {

        Optional<String> inpath = Stream.of(System.getenv("PATH").split(File.pathSeparator)).filter(p -> new File(p,bin).exists()).findFirst();

        String fullbin = new File(inpath.orElseThrow(IllegalArgumentException::new).toString(), bin).getAbsolutePath();

        log.info("ngrok found in path at " + fullbin);

        List<String> arguments = asList(fullbin, "start", "--none", "--log=stdout");

        ProcessBuilder pb = new ProcessBuilder(arguments);
        
        ngrok_process = pb.start();

        ProcessTask task = new ProcessTask(ngrok_process.getInputStream());
        executor = Executors.newSingleThreadExecutor();
        executor.submit(task);
        
        if(ngrok_process.isAlive()) {
            log.info("ngrok running");
        }
    }

    void  stop(@Observes ShutdownEvent ev) {
        if(ngrok_process!=null) {
            ngrok_process.destroy();
        }

        if(executor!=null) {
            executor.shutdown();
        }
    }

    public boolean isStarted() {
        return ngrok_process!=null && ngrok_process.isAlive();
    }

    private class ProcessTask implements Callable<Integer> {

        Pattern starting = Pattern.compile("starting web service.*addr=(\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+)");
        Pattern ready = Pattern.compile("client session established.* id=(.*)");


        private InputStream inputStream;

        public ProcessTask(InputStream is) {
            this.inputStream = is;
        }

        void handleData(String data) {
            try {
                Matcher m = starting.matcher(data);
                if (m.find()) {
                    lastAddress = "http://" + m.group(1);
                } else {
                    m = ready.matcher(data);
                    if(m.find()) {
                        connectEvent.fire(new NgrokReadyEvent(lastAddress));
                    }
                }
               // log.info(data); // log what we don't know.

            } catch(Exception e) {
                log.error("Error while processing ngrok stream", e);
            }
        }

        @Override
        public Integer call() {
                new BufferedReader(new InputStreamReader(inputStream))
                        .lines()
                        .forEach(this::handleData);
                return 0;

        }
    }
}