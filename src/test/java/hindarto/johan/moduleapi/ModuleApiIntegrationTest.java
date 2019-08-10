package hindarto.johan.moduleapi;

import hindarto.johan.Application;
import hindarto.johan.moduleapi.controller.ModuleController;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:test-application.properties")
public class ModuleApiIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testUserCorrectSetup() throws Exception {

        mvc.perform(
                get("/api/0.1/users/19")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is("Alex")))
                .andExpect(jsonPath("$.groupModule.modules.length()", Is.is(5)));
    }

    @Test
    public void testModuleCorrectSetup() throws Exception {

        mvc.perform(
                get("/api/0.1/modules/19")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.modules[0].moduleName", Is.is("PromoCard")))
                .andExpect(jsonPath("$.modules[0].moduleOrder", Is.is(1)))
                .andExpect(jsonPath("$.modules[2].moduleName", Is.is("FlashSaleCard")))
                .andExpect(jsonPath("$.modules[2].moduleOrder", Is.is(3)))
                .andExpect(jsonPath("$.modules[4].moduleName", Is.is("NewsCard")))
                .andExpect(jsonPath("$.modules[4].moduleOrder", Is.is(5)));
    }

    @Test
    public void testExceptionHandledGracefully() throws Exception {

        mvc.perform(
                get("/api/0.1/modules/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", Is.is(404)));
    }


}
