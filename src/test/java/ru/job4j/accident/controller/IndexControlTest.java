package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.accident.Job4jCarAccidentApplication;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.Rule;
import javax.servlet.http.HttpServletRequest;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
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
        this.mockMvc.perform(get("addRule"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addRule"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageRulePost() throws Exception {
        this.mockMvc.perform(post("/createRule")
                .param("id", "1")
                .param("name", "Статья 4"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Rule> argument = ArgumentCaptor.forClass(Rule.class);
        verify(accidentController).createRule(argument.capture());
        assertThat(argument.getValue().getId(), is("1"));
        assertThat(argument.getValue().getName(), is("Статья 4"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAccidentTypeGet() throws Exception {
        this.mockMvc.perform(get("createAccidentType"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccidentType"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAccidentTypePost() throws Exception {
        this.mockMvc.perform(post("/saveAccidentType")
                .param("id", "1")
                .param("name", "Авария"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<AccidentType> argument = ArgumentCaptor.forClass(AccidentType.class);
        verify(accidentController).saveAccidentType(argument.capture());
        assertThat(argument.getValue().getId(), is("1"));
        assertThat(argument.getValue().getName(), is("Авария"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAccidentGet() throws Exception {
        this.mockMvc.perform(get("createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAccidentPost() throws Exception {
        this.mockMvc.perform(post("/saveAccident")
                        .param("id", "1")
                        .param("name", "Столкновение двух машин"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        HttpServletRequest session = mock(HttpServletRequest.class);
        MultipartFile file = mock(MultipartFile.class);
        verify(accidentController).saveAccident(argument.capture(), file, session);
        assertThat(argument.getValue().getName(), is("Две машины."));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageUpdateRuleGet() throws Exception {
        this.mockMvc.perform(get("/updateRule"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("updateRule"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageUpdateRulePost() throws Exception {
        this.mockMvc.perform(post("/createRule")
                .param("id", "1")
                .param("name", "Статья 5"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Rule> save = ArgumentCaptor.forClass(Rule.class);
        verify(accidentController).createRule(save.capture());
        assertThat(save.getValue().getId(), is("1"));
        assertThat(save.getValue().getName(), is("Статья 5"));
        this.mockMvc.perform(post("/changeRule")
                .param("id", "1")
                .param("name", "Измененная статья"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Rule> update = ArgumentCaptor.forClass(Rule.class);
        verify(accidentController).changeRule(update.capture());
        assertThat(update.getValue().getId(), is("1"));
        assertThat(update.getValue().getName(), is(""));

    }

    @Test
    @WithMockUser
    public void shouldReturnMessageSaveAuthorityGet() throws Exception {
        this.mockMvc.perform(get("/createAuthority"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAuthority"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMessageCreateAuthorityPost() throws Exception {
        this.mockMvc.perform(post("/saveAuthority")
                        .param("id", "1")
                        .param("authority", "authority"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Authority> save = ArgumentCaptor.forClass(Authority.class);
        verify(accidentController).saveAuthority(save.capture());
        assertThat(save.getValue(), is("1"));
    }
}