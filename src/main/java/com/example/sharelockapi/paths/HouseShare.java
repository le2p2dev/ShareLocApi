package com.example.sharelockapi.paths;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.example.sharelockapi.controllers.HouseShareManager;
import com.example.sharelockapi.controllers.UserHasHouseShareManager;
import com.example.sharelockapi.controllers.UserManager;
import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.model.UserEntity;
import com.example.sharelockapi.model.UserHasHouseshareEntity;
import com.example.sharelockapi.security.JWTokenUtility;
import com.example.sharelockapi.security.SignNeeded;

@Path("/houseshare")
public class HouseShare {


    @GET
    @SignNeeded
    @Path("/index")
    @Produces(MediaType.APPLICATION_JSON)
    public Response indexByUser(@Context SecurityContext security) {

        //getting houseshare of user
        UserHasHouseshareEntity userHasHouseshareEntity =
                UserHasHouseShareManager.getUserHouseShareById(
                        UserManager.getUser(security.getUserPrincipal().getName()).getId()
                );
        HouseshareEntity houseShare = HouseShareManager.getHouseShareById(userHasHouseshareEntity.getHouseShareId());

        return  Response.status(Status.OK).entity(houseShare).build();
    }




    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(@QueryParam("login") String login) {

        return Response.status(Status.OK).build();

    }


}
