package ru.job4j.accident.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accident.Job4jCarAccidentApplication;
import ru.job4j.accident.model.User;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Job4jCarAccidentApplication.class)
@AutoConfigureMockMvc
public class RegControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegControl regControl;

    @Test
    @WithMockUser
    public void regPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(
                        "reg"));
    }

    @Test
    @WithMockUser
    public void setRegControl() throws Exception {
        this.mockMvc.perform(post("/reg")
                .param("username", "user")
                .param("password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(regControl.regSave(argument.capture()));
        Assert.assertEquals(argument.getValue().getUsername(), "user");
        Assert.assertEquals(argument.getValue().getPassword(), "password");
    }
}