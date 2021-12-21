package com.example.sharelockapi.paths;

import com.example.sharelockapi.controllers.*;
import com.example.sharelockapi.model.*;
import com.example.sharelockapi.security.SignNeeded;

import javax.ws.rs.*;
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

    @POST
    @SignNeeded
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response deleteTask(@Context SecurityContext security,@FormParam("id") int id){
        //TODO : check if this user has access to this task
        //getting user and the task
        UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());
        TaskEntity taskEntity = TaskManager.getTaskById(id);
        if(taskEntity == null){
            return Response.status(Response.Status.CONFLICT).entity("problem occured").build();
        }
        //TaskManager.deleteTask()
        return Response.status(Response.Status.CONFLICT).entity("problem occured").build();

    }

    @POST
    @SignNeeded
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response createTask(@Context SecurityContext security,@FormParam("idHouseShare") int idHouseShare,@FormParam("idCategory") int idCategory,@FormParam("point") int point,@FormParam("title") String title,@FormParam("description") String description){
        int id = TaskManager.getTasks().size();
        UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());
        HouseshareEntity houseshare = HouseShareManager.getHouseShareById(idHouseShare);
        CategoryEntity category = CategoryManager.getCategoryById(idCategory);

        if(TaskManager.createTask(id,houseshare,category,point,description,title)){
            return Response.status(Response.Status.OK).entity("done").build();
        }
        return Response.status(Response.Status.CONFLICT).entity("problem occured").build();

    }
}