package com.sise.ribbonClient;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

@SuppressWarnings("deprecation")
public class RibbonClientApplication {

    public static void main(String[] args) throws Exception {
//        ConfigurationManager.getConfigInstance().setProperty(
//                "my-client.ribbon.listOfServers",
//                "localhost:8081,localhost:8085"
//        );
        ConfigurationManager.loadPropertiesFromResources("application.yml");
        RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");
        HttpRequest request = HttpRequest.newBuilder().uri("/1").build();
        for(int i = 0; i < 6; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            String result = response.getEntity(String.class);
            System.out.println(result);
        }
    }

}
