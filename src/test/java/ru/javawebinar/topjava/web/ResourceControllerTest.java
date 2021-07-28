package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    void getResource() throws Exception {
        perform(get("/resources/css/style.css"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/css;charset=UTF-8"));
    }
}
