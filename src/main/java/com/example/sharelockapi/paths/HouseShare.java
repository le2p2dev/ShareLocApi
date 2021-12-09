package com.example.sharelockapi.paths;


import java.util.ArrayList;
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
import com.example.sharelockapi.security.SignNeeded;

@Path("/houseshare")
public class HouseShare {


    @GET
    @SignNeeded
    @Path("/index")
    @Produces(MediaType.APPLICATION_JSON)
    public Response indexByUser(@Context SecurityContext security) {

        //getting user
        UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());
        //Now that we have our user, we have to get houseshares using UserHasHouseshare
        List<UserHasHouseshareEntity> list = UserHasHouseShareManager.getUserHousShareByUserId(user.getId());
        List<HouseshareEntity> houseShareList = new ArrayList<>();
        for(UserHasHouseshareEntity u : list){
            houseShareList.add(HouseShareManager.getHouseShareById(u.getHouseshareId()));
        }

        if(houseShareList.size() == 0){
            return Response.status(Status.CONFLICT).entity("no houseshare founded").build();
        }
        return Response.status(Status.OK).entity(houseShareList).build();
    }


    @POST
    @SignNeeded
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @QueryParam("name") String name,
            @QueryParam("description") String description,
            @Context SecurityContext security
    ) {

        //Create an occurence of a new HouseShare
        //then create userHasHouseShare  to link user and his houser share
        UserEntity u = UserManager.getUser(security.getUserPrincipal().getName());
        int id = HouseShareManager.getHouseShares().size();
        boolean bool = HouseShareManager.createHouseShare(name, description);
        System.out.println("Gros zizi");
        System.out.println(HouseShareManager.getHouseShareById(id));
        System.out.println(HouseShareManager.getHouseShareById(id));
        System.out.println(HouseShareManager.getHouseShareById(id));
        if (bool) {
            //create userHasHouseShare
            if (
                    UserHasHouseShareManager.createUserHasHouseShare
                            (id,u, HouseShareManager.getHouseShareById(id), 0, 1)
            )
                return Response.status(Status.OK).entity(HouseShareManager.getHouseShareById(id)).build();
            return Response.status(Status.CONFLICT).entity("HouseShare builded but not link with user").build();
        }
        return Response.status(Status.CONFLICT).entity(HouseShareManager.getHouseShareById(id)).build();

    }


}
