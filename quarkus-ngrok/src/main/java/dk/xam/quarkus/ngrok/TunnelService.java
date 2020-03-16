package dk.xam.quarkus.ngrok;


import dk.xam.quarkus.ngrok.model.Options;
import dk.xam.quarkus.ngrok.model.Tunnel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Path("/api")
public interface TunnelService {

    @POST
    @Path("/tunnels")
    @Consumes(MediaType.APPLICATION_JSON)
    public Tunnel tunnels(Options opts);

}
