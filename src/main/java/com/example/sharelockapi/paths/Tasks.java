package com.example.sharelockapi.paths;

import com.example.sharelockapi.controllers.HouseShareManager;
import com.example.sharelockapi.controllers.TaskManager;
import com.example.sharelockapi.controllers.UserHasHouseShareManager;
import com.example.sharelockapi.controllers.UserManager;
import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.model.TaskEntity;
import com.example.sharelockapi.model.UserEntity;
import com.example.sharelockapi.model.UserHasHouseshareEntity;
import com.example.sharelockapi.security.SignNeeded;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/tasks")
public class Tasks {


    @GET
    @SignNeeded
    @Path("/index")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tasksByHouseShare(@Context SecurityContext security, @QueryParam("idHouseShare") int idHouseShare) {
        //TODO : check if its the right user
        //getting user
        UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());

        //if userHasHouseShare is empty return error access
        List<UserHasHouseshareEntity> list = UserHasHouseShareManager.getUserHousShareByUserId(user.getId());
        boolean noHouseShare = true;
        for(UserHasHouseshareEntity u : list){
            if(u.getHouseshareId().equals(idHouseShare)){
                noHouseShare = false;
            }
        }
        if(noHouseShare){
            return Response.status(Response.Status.CONFLICT).entity("you dont have permissions to see theses tasks").build();
        }
        //get task by idHouseShare
        List<TaskEntity> list1 = TaskManager.getTasksByHouseShareId(idHouseShare);
        return Response.status(Response.Status.OK).entity(list1).build();
    }
}