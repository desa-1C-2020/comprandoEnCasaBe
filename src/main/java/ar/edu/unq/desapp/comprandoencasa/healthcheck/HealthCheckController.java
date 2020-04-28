package ar.edu.unq.desapp.comprandoencasa.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static ar.edu.unq.desapp.comprandoencasa.extensions.MapExtensions.mapOf;
import static ar.edu.unq.desapp.comprandoencasa.extensions.MapExtensions.pair;


@RestController
public class HealthCheckController {
    @GetMapping("/health")
    public Map health() {
        return mapOf(pair("status", "OK"));
    }
}
