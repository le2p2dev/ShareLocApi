package com.example.sharelockapi.paths;

import com.example.sharelockapi.controllers.UserManager;
import com.example.sharelockapi.dao.DAOUser;
import com.example.sharelockapi.model.UserEntity;
import com.example.sharelockapi.security.SignNeeded;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/hello-world")
public class HelloRessource {

    //@SignNeeded
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.status(200).entity(UserManager.getUser(0).getFirstname()).build();

    }
}