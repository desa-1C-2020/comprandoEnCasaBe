package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.service.CommerceFinder;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CommercesController.basePath)
public class CommercesController {
    public static final String basePath = "/commerces";

    @Autowired
    private CommerceFinder commerceFinder;

    @GetMapping("findInRange")
    public List<Commerce> getAllWithinGivenDistance(@RequestParam("maxDistance") String maxDistanceMeters,
                                                    @RequestParam String latitud,
                                                    @RequestParam String longitud) {

//        String uid = principal.getName();
        double latitudParsed = Double.parseDouble(latitud);
        double longitudParsed = Double.parseDouble(longitud);
        LatLng latLngFrom = new LatLng(latitudParsed, longitudParsed);
        return commerceFinder.findAllInsideRange(maxDistanceMeters, latLngFrom);
    }
}

