package ar.edu.unq.desapp.comprandoencasa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring App entrypoint
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ComprandoEnCasaApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ComprandoEnCasaApplication.class).run(args);
    }
}
