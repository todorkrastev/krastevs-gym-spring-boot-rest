package com.todorkrastev.krastevsgymrest.web;

import com.jayway.jsonpath.JsonPath;
import com.todorkrastev.krastevsgymrest.model.entity.Activity;
import com.todorkrastev.krastevsgymrest.repository.ActivityRepository;
import com.todorkrastev.krastevsgymrest.util.BearerTokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerIntegrationTest {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllActivities() throws Exception {
        mockMvc.perform(get("/api/v1/activities/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(activityRepository.findAll().getFirst().getId().intValue())))
                .andExpect(jsonPath("$[0].title", is(activityRepository.findAll().getFirst().getTitle())))
                .andExpect(jsonPath("$[0].description", is(activityRepository.findAll().getFirst().getDescription())))
                .andExpect(jsonPath("$[0].imageURL", is(activityRepository.findAll().getFirst().getImageURL())));
    }

    @Test
    public void testGetActivityById_200() throws Exception {
        Activity activity = activityRepository.save(
                new Activity()
                        .setTitle("Title: testGetActivityById__200")
                        .setDescription("Description")
                        .setImageURL("Image URL"));


        mockMvc.perform(get("/api/v1/activities/{id}", activity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(activity.getId().intValue())))
                .andExpect(jsonPath("$.title", is(activity.getTitle())))
                .andExpect(jsonPath("$.description", is(activity.getDescription())))
                .andExpect(jsonPath("$.imageURL", is(activity.getImageURL())));
    }

    @Test
    public void testGetActivityById_404() throws Exception {
        mockMvc.perform(get("/api/v1/activities/{id}", "500000000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetActivityById_400() throws Exception {
        mockMvc.perform(get("/api/v1/activities/{id}", "baba")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateActivityById_200() throws Exception {
        final String bearerToken = BearerTokenGenerator.generateToken("testUser", "ROLE_ADMIN");

        Activity activity = activityRepository.save(
                new Activity()
                        .setTitle("Title: testUpdateActivityById__200")
                        .setDescription("Description")
                        .setImageURL("Image URL"));

        Long activityId = activity.getId();

        ResultActions actions = mockMvc.perform(put("/api/v1/activities/{id}", activityId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + bearerToken)
                .content("""
                          {
                            "title": "Title: testUpdateActivityById_UPDATED",
                            "description": "Description_UPDATED",
                            "imageURL": "Image URL_UPDATED"
                          }
                        """));

        MvcResult mvcResult = actions.andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id", is(activityId.intValue())))
                .andReturn();

        String contentAsString = mvcResult
                .getResponse()
                .getContentAsString();

        int id = JsonPath.read(contentAsString, "$.id");

        Optional<Activity> createdActivityOptional = activityRepository.findById((long) id);

        assertTrue(createdActivityOptional.isPresent());

        Activity createdActivity = createdActivityOptional.get();

        assertEquals("Title: testUpdateActivityById_UPDATED", createdActivity.getTitle());
        assertEquals("Description_UPDATED", createdActivity.getDescription());
        assertEquals("Image URL_UPDATED", createdActivity.getImageURL());
    }

    @Test
    public void testCreateActivity_201() throws Exception {
        final String bearerToken = BearerTokenGenerator.generateToken("testUser", "ROLE_ADMIN");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/activities/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + bearerToken)
                        .content("""
                                  {
                                    "title": "Title: testCreateActivity",
                                    "description": "Description",
                                    "imageURL": "Image URL"
                                  }
                                """)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String contentAsString = mvcResult
                .getResponse()
                .getContentAsString();

        int id = JsonPath.read(contentAsString, "$.id");

        Optional<Activity> createdActivityOptional = activityRepository.findById((long) id);

        assertTrue(createdActivityOptional.isPresent());

        Activity createdActivity = createdActivityOptional.get();

        assertEquals("Title: testCreateActivity", createdActivity.getTitle());
        assertEquals("Description", createdActivity.getDescription());
        assertEquals("Image URL", createdActivity.getImageURL());
    }

    @Test
    public void testCreateActivity_403() throws Exception {
        mockMvc.perform(post("/api/v1/activities/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                          {
                            "title": "Title: testCreateActivity",
                            "description": "Description",
                            "imageURL": "Image URL"
                          }
                        """)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void testDeleteActivityById() throws Exception {
        final String bearerToken = BearerTokenGenerator.generateToken("testUser", "ROLE_ADMIN");

        mockMvc.perform(delete("/api/v1/activities/{id}", "1")
                        .header("Authorization", "Bearer " + bearerToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(activityRepository.findById(1L).isPresent());
    }

}