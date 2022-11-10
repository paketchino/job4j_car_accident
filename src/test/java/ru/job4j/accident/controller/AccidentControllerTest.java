package ru.job4j.accident.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.accident.Job4jCarAccidentApplication;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.controller.AccidentController;
import ru.job4j.accident.service.AccidentTypeServiceData;
import ru.job4j.accident.service.RuleServiceData;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest(classes = Job4jCarAccidentApplication.class)
@AutoConfigureMockMvc

public class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentController accidentController;

    @MockBean
    private RuleServiceData ruleServiceData;

    @MockBean
    private AccidentTypeServiceData accidentTypeServiceData;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/index"));
    }

    @Test
    @WithMockUser
    public void saveAccident() throws Exception {
        this.mockMvc.perform(post("/saveAccident")
                        .param("id", "1")
                        .param("name", "Столкновение")
                        .param("rule.id", "Статья 1")
                        .param("accidentType.id", "Аварийная ситуация")
                        .param("address", "ул. Колотушкино")
                        .param("numberCar", "12345")
                        .param("description",
                                "С участием двух посторонних лиц")
                        .param("photo", "1221334")
                        .param("status", "false"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor
                .forClass(Accident.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        MultipartFile multipartFile = mock(MultipartFile.class);
        verify(accidentController)
                .saveAccident(argument.capture(), multipartFile, request);
        Assert.assertEquals(argument.getValue().getName(), "Столкновение");
    }

    @Test
    @WithMockUser
    public void createRule() throws Exception {
        this.mockMvc.perform(post("/createRule")
                .param("id", "1")
                .param("name", "Статья 1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Rule> argument = ArgumentCaptor.forClass(Rule.class);
        verify(ruleServiceData).save(argument.capture());
        Assert.assertEquals(argument.capture().getName(), "Статья 1");
    }

    @Test
    @WithMockUser
    public void saveAccidentType() throws Exception {
        this.mockMvc.perform(post("/saveAccidentType")
                        .param("id", "1")
                        .param("name", "Авария"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<AccidentType> argument = ArgumentCaptor.forClass(AccidentType.class);
        verify(accidentController).saveAccidentType(argument.capture());
        Assert.assertEquals(argument.capture().getName(), "Авария");
    }

    @Test
    @WithMockUser
    public void saveAuthority() throws Exception {
        this.mockMvc.perform(post("/saveAuthority")
                        .param("id", "1")
                        .param("name", "Админ"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Authority> argument = ArgumentCaptor.forClass(Authority.class);
        verify(accidentController).saveAuthority(argument.capture());
        Assert.assertEquals(argument.capture().getAuthority(), "Админ");
    }
}