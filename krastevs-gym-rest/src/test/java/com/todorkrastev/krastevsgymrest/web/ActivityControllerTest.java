package com.todorkrastev.krastevsgymrest.web;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails = new User("testuser", "", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void testGetAllActivities() {
        ActivityDTO activityDTO = new ActivityDTO().setId(1L).setTitle("Yoga");
        when(activityService.findAll()).thenReturn(List.of(activityDTO));

        ResponseEntity<List<ActivityDTO>> response = activityController.getAllActivities(userDetails);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Yoga", response.getBody().getFirst().getTitle());
        verify(activityService, times(1)).findAll();
    }

    @Test
    void testGetActivityById() {
        ActivityDTO activityDTO = new ActivityDTO().setId(1L).setTitle("Yoga");
        when(activityService.getActivityById(1L)).thenReturn(activityDTO);

        ResponseEntity<ActivityDTO> response = activityController.getActivityById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Yoga", response.getBody().getTitle());
        verify(activityService, times(1)).getActivityById(1L);
    }

    @Test
    void testUpdateActivityById() {
        ActivityDTO activityDTO = new ActivityDTO().setId(1L).setTitle("Updated Yoga");
        when(activityService.updateActivityById(eq(1L), any(ActivityDTO.class))).thenReturn(activityDTO);

        ResponseEntity<ActivityDTO> response = activityController.updateActivityById(1L, activityDTO);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Updated Yoga", response.getBody().getTitle());
        verify(activityService, times(1)).updateActivityById(eq(1L), any(ActivityDTO.class));
    }

    @Test
    void testCreateActivity() {
        ActivityDTO activityDTO = new ActivityDTO().setId(1L).setTitle("Yoga");
        when(activityService.createActivity(any(ActivityDTO.class))).thenReturn(activityDTO);

        ResponseEntity<ActivityDTO> response = activityController.createActivity(activityDTO);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Yoga", response.getBody().getTitle());
        verify(activityService, times(1)).createActivity(any(ActivityDTO.class));
    }

    @Test
    void testDeleteActivityById() {
        doNothing().when(activityService).deleteActivityById(1L);

        ResponseEntity<ActivityDTO> response = activityController.deleteActivityById(1L);

        assertEquals(204, response.getStatusCode().value());
        verify(activityService, times(1)).deleteActivityById(1L);
    }
}