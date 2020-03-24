package com.codeup.springtest;

import com.codeup.springtest.controllers.SpringTestApplication;
import com.codeup.springtest.models.Post;
import com.codeup.springtest.models.User;
import com.codeup.springtest.repositories.PostRepository;
import com.codeup.springtest.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// SpringRunner is an alias for the SpringJUnit4ClassRunner, which joins the JUnit testing library with the Spring TestContext Framework.
@RunWith(SpringRunner.class)
// This annotation tells the framework which Java Class with a main method starts the application. You can find this file under a path like this
@SpringBootTest (classes = SpringTestApplication.class)
// This is an annotation that can be applied to a test class to enable and configure auto-configuration of MockMvc.
@AutoConfigureMockMvc
class SpringTestApplicationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    PostRepository postDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() throws Exception {

        testUser = userDao.findUserByUsername("testUser");
        // create the test user if it does not exist
        if (testUser == null) {
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@email.com");
            testUser = userDao.save(newUser);
        }

        // Throw a Post request to /login and expect a redirection to the posts index page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testUser")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void contextLoads() {
        // Sanity Test, just to make sure the MVC bean is working
        assertNotNull(mvc);
//        assertNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        assertNotNull(httpSession);
    }

    @Test
    public void testCreatePost() throws Exception {
        // Make a Post request to /posts/create and expect a redirection to the Post
        this.mvc.perform(
                post("/posts/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "test")
                .param("body","for sale"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testEditPost() throws Exception {
        // Get any post for testing purposes
        Post existingPost = postDao.findAll().get(0);

        // Makes a Post request to /posts/edit/{id} and expect a redirection as a result
        this.mvc.perform(
                post("/posts/edit/" + existingPost.getId()).with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "edited title")
                .param("body", "edited body"))
                .andExpect(status().is3xxRedirection());
        System.out.println("here");
        // Makes a Get request to /posts/{id} and expect redirection to the Post show page
        System.out.println(existingPost.getTitle());
        System.out.println(existingPost.getId());
        String url = "/posts/" + existingPost.getId();
        System.out.println(url);
        this.mvc.perform(
                get(url))
//                .andExpect(status().isOk())
                .andExpect(content().string(containsString("edited title")))
                .andExpect(content().string(containsString("edited body")));
    }
}
