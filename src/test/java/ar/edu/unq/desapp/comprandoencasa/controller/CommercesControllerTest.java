package ar.edu.unq.desapp.comprandoencasa.controller;

import ar.edu.unq.desapp.comprandoencasa.controllers.CommercesController;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Test que prueba hasta el repositorio. Sería mejor mockear algunas cosas.
 */
public class CommercesControllerTest extends SpringIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenFindCommercesInRange_thenReturnsValidCommercesCorrectly() throws Exception {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.put("maxDistance", singletonList("3000"));
        queryParams.put("latitud", singletonList("-34.7040003"));
        queryParams.put("longitud", singletonList("-58.2754042"));
        MvcResult mvcResult = mockMvc.perform(get(CommercesController.basePath + "/findInRange")
            .params(queryParams))
            .andExpect(status().isOk())
            .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        Commerce[] commerce = objectMapper.readValue(responseContent, Commerce[].class);
        assertThat(commerce.length, equalTo(3));
    }
}