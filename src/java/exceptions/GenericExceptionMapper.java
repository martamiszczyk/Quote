/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author martamiszczyk
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> 
{

    @Context
    ServletContext context;
    
    @Override
    public Response toResponse(Throwable e)
    {
        JsonObject jo = new JsonObject();
        if(Boolean.valueOf(context.getInitParameter("debug"))){
            jo.addProperty("StackTrace", e.getStackTrace().toString());
        }
        jo.addProperty("Message", e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).build(); 
    }
    
}
