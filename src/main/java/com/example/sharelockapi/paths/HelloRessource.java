package com.example.sharelockapi.paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/hello-world")
public class HelloRessource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        return Response.status(200).entity("hello-world").build();
    }
}