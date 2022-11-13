package ru.job4j.accident.controller;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.job4j.accident.Job4jCarAccidentApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Job4jCarAccidentApplication.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginController loginController;

    @Ignore
    @Test
    @WithMockUser
    public void loginPage() throws Exception {
        this.mockMvc.perform(get("/login?error=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("errorMessage",
                        "Username or Password is incorrect !!"));
    }

    @Ignore
    @Test
    @WithMockUser
    public void logoutPage() throws Exception {
        this.mockMvc.perform(get("/login?logout=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("errorMessage",
                        "You have been successfully logged out !!"));
    }
}