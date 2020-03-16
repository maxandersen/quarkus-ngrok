package dk.xam.quarkus.ngrok;

import org.jboss.weld.context.http.Http;

import java.util.HashMap;
import java.util.Map;

public class TunnelMetrics {

    private Conns conns;
    
    private TunnelHttp http;

    
    public Conns getConns() {
        return conns;
    }

    
    public void setConns(Conns conns) {
        this.conns = conns;
    }

    
    public Http getHttp() {
        return http;
    }

    
    public void setHttp(Http http) {
        this.http = http;
    }

}