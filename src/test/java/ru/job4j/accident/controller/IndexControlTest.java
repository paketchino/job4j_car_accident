package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accident.Job4jCarAccidentApplication;
import ru.job4j.accident.model.Accident;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Job4jCarAccidentApplication.class)
@AutoConfigureMockMvc
public class IndexControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentController accidentController;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageRuleGet() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAccidentTypeGet() throws Exception {
        this.mockMvc.perform(get("/formUpdateAccident")
                        .param("id", "1")
                        .param("file", "file"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("formUpdateAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAccidentGet() throws Exception {
        this.mockMvc.perform(get("/addRule"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addRule"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageCreateAccidentTypeGet() throws Exception {
        this.mockMvc.perform(get("/createAccidentType"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccidentType"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageSaveAuthorityGet() throws Exception {
        this.mockMvc.perform(get("/createAuthority"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAuthority"));
    }

}