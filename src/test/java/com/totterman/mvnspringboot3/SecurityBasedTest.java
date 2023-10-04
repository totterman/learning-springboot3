package com.totterman.mvnspringboot3;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HomeController.class)
public class SecurityBasedTest {
    @Autowired MockMvc mvc;
    @MockBean VideoService videoService;

    @Test
    public void unauthUserShouldNotAccessHomePage() throws Exception {
        mvc
                .perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "alice", roles = "USER")
    public void authUserShouldAccessHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "alice", roles = "ADMIN")
    public void adminShouldAccessHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void newVideoFromUnauthUserShouldFail() throws Exception {
        mvc.perform(post("/new-video")
                .param("name", "new video")
                .param("description", "new desc")
                .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "alice", roles = "USER")
    public void newVideoFromAuthUserShouldWork() throws Exception {
        mvc.perform(post("/new-video")
                        .param("name", "new video")
                        .param("description", "new desc")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}
