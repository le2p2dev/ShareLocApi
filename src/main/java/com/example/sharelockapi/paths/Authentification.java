package com.example.sharelockapi.paths;



import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.example.sharelockapi.controllers.UserManager;
import com.example.sharelockapi.model.UserEntity;
import com.example.sharelockapi.security.JWTokenUtility;
import com.example.sharelockapi.security.SignNeeded;

@Path("/")
public class Authentification {

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Test(){
        return Response.status(Status.OK).entity("Everything Working").build();
    }

    @POST
    @Path("/testForm")
    @Consumes("application/x-www-form-urlencoded")
    public Response postTest(@FormParam("name") String name){
            return Response.status(Status.OK).entity(name).build();
    }

    @GET
    @SignNeeded
    @Path("/whoami")
    @Produces(MediaType.APPLICATION_JSON)
    public Response whoami(@Context SecurityContext security) {
        try {
            System.err.println(">> whoami");
            UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());
            return Response.ok().entity(user).build();
        } catch (NullPointerException e) {
            return Response.status(Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response signin(@FormParam("login") String login, @FormParam("password") String password) {
        UserEntity u = UserManager.login(login, password);

        if (u != null)
            return Response.ok().entity(JWTokenUtility.buildJWT(u.getLogin())).build();

        return Response.status(Status.NOT_ACCEPTABLE).build();
    }

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response signup(@FormParam("login") String login, @FormParam("password") String password,
                           @FormParam("firstname") String firstname, @FormParam("lastname") String lastname) {


        int id = UserManager.getUsers().size();

        if (UserManager.createUser(login, password, firstname, lastname,id))
            return Response.status(Status.OK).entity("done").build();
        return Response.status(Status.CONFLICT).build();

    }

    /**
     * Méthode permettant de récupérer l'ensemble des roles d'un utilisateur
     *
     * @param user l'utilisateur
     * @return une liste de tous les roles associés à l'utilisateur user
     */
    public static List<String> findUserRoles(String user) {
        return null;
    }
}
