package ar.edu.unq.desapp.comprandoencasa.healthcheck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = HealthCheckController.class)
public class HealthCheckControllerTest {

    @Autowired
    private MockMvc webClient;

    @Test
    public void itReturnsStatusOk() throws Exception {
        webClient.perform(get("/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is("OK")))
            .andReturn()
            .getResponse().getContentAsString();
    }
}