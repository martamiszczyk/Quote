/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.QuoteNotFoundException;
import exceptions.ServerError;
import static java.lang.Math.random;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author martamiszczyk
 */
@Path("quote")
public class GenericResource
{

    @Context
    private UriInfo context;

    static int nextId = 10;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource()
    {
    }

    public static Map<Integer, String> quotes = new HashMap()
    {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getQuote(@PathParam("id") int id) throws QuoteNotFoundException, ServerError
    {
        if (quotes.containsKey(id))
        {
            JsonObject quote = new JsonObject();
            quote.addProperty("quote", quotes.get(id));
            String jsonResponse = new Gson().toJson(quote);
            return jsonResponse;
        } 
//        else if (id == 5)
//        {
//            throw new ServerError("Internal server Error, we are very sorry for the inconvenience");
//        } 
        else
        {
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
    }

    @GET
    @Produces("application/json")
    public String getQuotes()
    {
        Random generator = new Random();
        Object[] values = quotes.values().toArray();
        Object randomValues = (String) values[generator.nextInt(values.length)];
        JsonObject quote = new JsonObject();
        String jsonResponse = new Gson().toJson(randomValues);
        return jsonResponse;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createQuote(String quote)
    {
        int quoteID;

        quoteID = nextId++;
        JsonObject newQuote = new JsonParser().parse(quote).getAsJsonObject();
        quote = newQuote.get("quote").getAsString();
        quotes.put(quoteID, quote);

        JsonObject response = new JsonObject();
        response.addProperty("id", quoteID);
        response.addProperty("quote", quotes.get(quoteID));
        String jsonResponse = new Gson().toJson(response);
        return jsonResponse;

    }

    @PUT
    @Path("{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public String updateQuote(@PathParam("id") int id, String quote)
    {

        if (quotes.containsKey(id))
        {

            JsonObject newQuote = new JsonParser().parse(quote).getAsJsonObject();
            quote = newQuote.get("quote").getAsString();
            quotes.put(id, quote);
        }

        JsonObject response = new JsonObject();
        response.addProperty("id", id);
        response.addProperty("quote", quotes.get(id));
        String jsonResponse = new Gson().toJson(response);
        return jsonResponse;
    }
}
/**
 * PUT method for updating or creating an instance of GenericResource
 *
 * @param content representation for the resource
 * @return an HTTP response with content of the updated or created resource.
 */
//    @PUT
//    @Consumes("application/json")
//    public void putQuote(String content)
//    {
//    }
//}
