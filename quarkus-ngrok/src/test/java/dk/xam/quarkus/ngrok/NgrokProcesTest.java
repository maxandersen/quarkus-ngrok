package dk.xam.quarkus.ngrok;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class NgrokProcesTest {

    @Inject
    Ngrok ngrok;

    @Inject
    NgrokProces ngrokprocess;

    @Test
    public void testProcess() {

        assertTrue(ngrokprocess.isStarted());

    }
    @Test
    public void testBasic() {
        ngrok.onConnect(null);
    }

    @Test
    public void testStartup() throws IOException, InterruptedException {
        NgrokProces ng = new NgrokProces();

        ng.start(null);

        //Thread.sleep(5000);
        ng.stop(null);
    }
}