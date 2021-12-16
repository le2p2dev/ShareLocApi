package com.example.sharelockapi.paths;

import com.example.sharelockapi.controllers.*;
import com.example.sharelockapi.model.*;
import com.example.sharelockapi.security.SignNeeded;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

public class Category {
    @GET
    @SignNeeded
    @Path("/index")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategory() {
        //TODO : get all category
        List<CategoryEntity> categoryEntities = CategoryManager.getCategories();
        return Response.status(Response.Status.OK).entity(categoryEntities).build();
    }

    @POST
    @SignNeeded
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response createCategory(@Context SecurityContext security,@FormParam("idHouseShare") String label ){
        int id = CategoryManager.getCategories().size();

        if(CategoryManager.createCategoriy(id,label)){
            return Response.status(Response.Status.OK).entity("done").build();
        }
        return Response.status(Response.Status.CONFLICT).entity("problem occured").build();

    }
}

