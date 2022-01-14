package com.example.sharelockapi.paths;



import java.util.List;
import java.util.Objects;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.example.sharelockapi.controllers.UserManager;
import com.example.sharelockapi.jsonObjects.JWTJson;
import com.example.sharelockapi.jsonObjects.UserJSON;
import com.example.sharelockapi.jsonObjects.UserSignupJSON;
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
    @Consumes("application/json")
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
            JWTJson jwtJson = new JWTJson(JWTokenUtility.buildJWT(user.getLogin()),user.getLogin());
            return Response.ok(jwtJson,MediaType.APPLICATION_JSON).entity(user).build();
        } catch (NullPointerException e) {
            return Response.status(Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signin(UserJSON userJSON) {

        //TODO : find user
        UserEntity user = UserManager.getUser(userJSON.login);
        if(Objects.equals(user.getPassword(), userJSON.password)){
            JWTJson jwtJson = new JWTJson(JWTokenUtility.buildJWT(user.getLogin()),user.getLogin());
            return Response.ok(jwtJson,MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
        /*
        UserEntity u = UserManager.login(login, password);
        if (u != null)
            return Response.ok().entity(JWTokenUtility.buildJWT(u.getLogin())).build();
         */
    }

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(UserSignupJSON userSignupJSON) {


        int id = UserManager.getUsers().size();

        if (UserManager.createUser(userSignupJSON.login,userSignupJSON.password, userSignupJSON.firstname, userSignupJSON.lastname,id))
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
