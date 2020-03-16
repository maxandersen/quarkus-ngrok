package dk.xam.quarkus.ngrok;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import dk.xam.quarkus.ngrok.model.Options;
import dk.xam.quarkus.ngrok.model.Tunnel;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Class to listen to events from NgrokProcess to know when its time to register connection.
 * Will take the default connection exposed by quarkus and forward.
 *
 */
@ApplicationScoped
public class Ngrok {

    Logger log = Logger.getLogger(Ngrok.class);

    List<String> tunnels = new ArrayList<>();

    String address;

    public void onConnect(@Observes NgrokReadyEvent event) {
        this.address = event.getAddress();
        System.out.println();

        Tunnel tunnel = setupTunnel();

        log.info(format("Web Interface: %s\n", address));
        log.info(format("Forwarding: %s -> %s", tunnel.getConfig().getAddr(), tunnel.getPublic_url()));

    }

    public Tunnel setupTunnel() {

        TunnelService simpleGetApi = RestClientBuilder.newBuilder()
                .baseUri(URI.create(address)).build(TunnelService.class);

        Options options = new Options();
        Integer port = ConfigProvider.getConfig().getValue("quarkus.http.port", Integer.class);

        String appname = ConfigProvider.getConfig().getValue("quarkus.application.name", String.class);

        options.setName(appname);
        options.setAddr(port.toString());

        return simpleGetApi.tunnels(options);
    }


}
