package ar.edu.unq.desapp.comprandoencasa.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AppController {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.clientId}")
    private String clientId;

    @RequestMapping(value = "/api/public", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String publicEndpoint() {
        return "Hello from a public endpoint! You don\'t need to be authenticated to see this.";
    }

    @RequestMapping(value = "/api/private", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String privateEndpoint() {
        return "Hello from a private endpoint! You need to be authenticated to see this.";
    }

    @RequestMapping(value = "/api/private-scoped", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String privateScopedEndpoint() {
        return "Hello from a private endpoint! You need to be authenticated and have a scope of read:messages to see this.";
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public HashMap<Object, Object> getAppConfigs() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("domain", domain);
        objectObjectHashMap.put("clientID", clientId);
        objectObjectHashMap.put("audience", resourceId);
        objectObjectHashMap.put("domain", domain);
        return objectObjectHashMap;
    }
}