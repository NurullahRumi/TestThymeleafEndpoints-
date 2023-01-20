package com.example.thymeleafendpoints.controller;

import com.example.thymeleafendpoints.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowAccessForAnonymousUser() throws Exception{
        this.mockMvc
                .perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers"))
                .andExpect(model().attributeExists("customer"))
                .andExpect(model().attributeExists("customers"));
    }

    void shouldCreateNewCustomer() throws Exception{
        this.mockMvc
                .perform(post("/customers")
                        .param("name","Rumi")
                        .param("number","10006694")
                        .param("email","rnurullah@gmail.com")
                        .with(csrf()))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(header().string("Location","/customers"));

    }
}
