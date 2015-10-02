/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import com.jayway.restassured.parsing.Parser;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.*;
import rest.GenericResource;

/**
 *
 * @author martamiszczyk
 */
public class RestTest
{

    public RestTest()
    {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/REST-1/api/quote";
    }

    @Test
    public void testGet()
    { 
        when()
            .get("/1")
        .then().       
            statusCode(200).
            body("quote",equalTo("Friends are kisses blown to us by angels"));
    }
    
    @Test
    public void testPost()
    {
        GenericResource rest= new GenericResource();
        rest.quotes.put(4, "hej");
        given()
                .contentType("application/json")
        .when()
                .post("/4")
        .then()
              .statusCode(200)
              .body("quote", equalTo("hej"));
                
    }
            
}


