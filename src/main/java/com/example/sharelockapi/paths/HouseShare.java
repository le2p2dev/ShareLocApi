package com.example.sharelockapi.paths;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.example.sharelockapi.controllers.HouseShareManager;
import com.example.sharelockapi.controllers.UserHasHouseShareManager;
import com.example.sharelockapi.controllers.UserManager;
import com.example.sharelockapi.jsonObjects.HouseShareJson;
import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.model.UserEntity;
import com.example.sharelockapi.model.UserHasHouseshareEntity;
import com.example.sharelockapi.security.SignNeeded;

@Path("/houseshare")
public class HouseShare {

    @DELETE
    @SignNeeded
    @Path("/delete")
    public Response deleteHouseShare(@Context SecurityContext security,@QueryParam("id") int id){

        UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());
        List<UserHasHouseshareEntity> listUserHouse = UserHasHouseShareManager.getUserHousShareByUserId(user.getId());
        boolean listUserisNull = false;

        if(listUserHouse == null){
            listUserisNull = true;
        }
        if( listUserisNull ){
            return Response.status(Response.Status.CONFLICT).build();
        }else{
            for(UserHasHouseshareEntity userHasHouseshare : listUserHouse){
                if(userHasHouseshare.getHouseshareId() == id && userHasHouseshare.getIsOwner() == 1 ){
                    HouseShareManager.delete(HouseShareManager.getHouseShareById(id));
                        return Response.status(Status.OK).entity("done").build();
                }
            }
    }
        return Response.status(Response.Status.CONFLICT).build();
    }


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
            return Response.status(Status.OK).entity("no houseshare founded").build();
        }
        return Response.status(Status.OK).entity(houseShareList).build();
    }


    @POST
    @SignNeeded
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(HouseShareJson houseShareJSON,
                           @Context SecurityContext security
    ) {

        //Create an occurence of a new HouseShare
        //then create userHasHouseShare  to link user and his houser share
        UserEntity u = UserManager.getUser(security.getUserPrincipal().getName());
        int id = HouseShareManager.getHouseShares().size();
        boolean bool = HouseShareManager.createHouseShare(houseShareJSON.name, houseShareJSON.description);
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
