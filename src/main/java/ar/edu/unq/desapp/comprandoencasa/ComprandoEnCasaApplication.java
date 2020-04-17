package ar.edu.unq.desapp.comprandoencasa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Spring App entrypoint
 */
@SpringBootApplication
public class ComprandoEnCasaApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(ComprandoEnCasaApplication.class).run(args);
  }
}
