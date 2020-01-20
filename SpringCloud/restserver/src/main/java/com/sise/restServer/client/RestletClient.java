package com.sise.restServer.client;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringCloud
 * @description: Lab5 Restlet
 * @author: wxy
 * @create: 2020-01-17 16:09
 **/

public class RestletClient {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) throws IOException {
        ClientResource clientResource = new ClientResource("http://localhost:8080/1");
        Representation representation = clientResource.get(MediaType.APPLICATION_JSON);
        JacksonRepresentation jacksonRepresentation = new JacksonRepresentation(representation, HashMap.class);
        Map result = (HashMap) jacksonRepresentation.getObject();
        System.out.println(result.get("id") + "-" + result.get("name") + "-" + result.get("age") + "-" + result.get("message"));
    }

}
