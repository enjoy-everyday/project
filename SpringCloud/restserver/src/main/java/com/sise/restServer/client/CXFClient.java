package com.sise.restServer.client;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: SpringCloud
 * @description: Lab5 CXF
 * @author: wxy
 * @create: 2020-01-17 15:12
 **/

public class CXFClient {

    public static void main(String[] args) throws IOException {
        WebClient webClient = WebClient.create("http://localhost:8080/1");
        Response response = webClient.get();
        InputStream inputStream = (InputStream) response.getEntity();
        String content = IOUtils.readStringFromStream(inputStream);
        System.out.println(content);
    }

}
