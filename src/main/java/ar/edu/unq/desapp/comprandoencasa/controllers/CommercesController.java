package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.model.DistanceCalculator;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
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
    CommerceRepository commerceRepository;

    @Autowired
    DistanceCalculator distanceCalculator;

    @GetMapping("findInRange")
    public List<Commerce> getAllWithinGivenDistance(@RequestParam String maxDistanceMeters,
                                                    @RequestParam String latitud,
                                                    @RequestParam String longitud) {
        List<Commerce> commerceList = commerceRepository.getAll();
        double latitudParsed = Double.parseDouble(latitud);
        double longitudParsed = Double.parseDouble(longitud);
        Long maxDistanceLong = Long.valueOf(maxDistanceMeters);
        return distanceCalculator.getInFrom(commerceList, latitudParsed, longitudParsed, maxDistanceLong);
    }
}

