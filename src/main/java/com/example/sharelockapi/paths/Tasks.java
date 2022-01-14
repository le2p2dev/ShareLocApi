package com.example.sharelockapi.paths;

import com.example.sharelockapi.controllers.*;
import com.example.sharelockapi.jsonObjects.RespJson;
import com.example.sharelockapi.jsonObjects.TaskJSON;
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
        /*
            1/ TODO : récupérer l'utilisateur courant
            2/ récupérer toutes les colloc de l'utilisateur
            3/ garder la colloc de la query param
            récuperer les tache associé à cette
         */
        UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());
        List<UserHasHouseshareEntity> listUserHouse = UserHasHouseShareManager.getUserHousShareByUserId(user.getId());
        List<UserHasHouseshareEntity> listofGoodHouseShare  = new ArrayList<>();
        boolean listUserisNull = false;

        if(listUserHouse == null){
            listUserisNull = true;
        }
        if( listUserisNull ){
            return Response.status(Response.Status.CONFLICT).build();
        }else{
            for(UserHasHouseshareEntity userHasHouseshare : listUserHouse){
                if(userHasHouseshare.getHouseshareId() == idHouseShare){
                    listofGoodHouseShare.add(userHasHouseshare);
                }
            }
            //we now have the right association user - colloc
            //we can get tasks
            List <TaskEntity> list = TaskManager.getTasks();
            List <TaskEntity> finalList = new ArrayList<TaskEntity>();
            if ( list == null){
                return Response.status(Response.Status.OK).entity("no tasks").build();
            }else{
                for(TaskEntity task : list){
                    if( task.getHouseShareId() == idHouseShare){
                        finalList.add(task);
                    }
                }
            }

            return Response.status(Response.Status.OK).entity(finalList).build();

       }
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

        try{
            TaskManager.deleteTask(TaskManager.getTaskById(id));
        }catch (Error e){
            System.out.println(e);
        }

        return Response.status(Response.Status.CONFLICT).entity("problem occured").build();

    }

    @POST
    @SignNeeded
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask(@Context SecurityContext security, TaskJSON taskJSON){
        int id = TaskManager.getTasks().size();
        UserEntity user = UserManager.getUser(security.getUserPrincipal().getName());
        HouseshareEntity houseshare = HouseShareManager.getHouseShareById(taskJSON.idHouseShare);
        CategoryEntity category = CategoryManager.getCategoryById(taskJSON.idCategory);

        if(TaskManager.createTask(id,houseshare,category,taskJSON.point,taskJSON.description,taskJSON.title)){
            return Response.ok(new RespJson("done"),MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.CONFLICT).entity("problem occured").build();

    }
}