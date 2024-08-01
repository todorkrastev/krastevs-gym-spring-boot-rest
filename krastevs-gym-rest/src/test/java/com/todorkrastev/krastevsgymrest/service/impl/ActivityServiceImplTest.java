package com.todorkrastev.krastevsgymrest.service.impl;

import com.todorkrastev.krastevsgymrest.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.model.entity.Activity;
import com.todorkrastev.krastevsgymrest.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ActivityServiceImpl activityService;

    private Activity activity;
    private ActivityDTO activityDTO;

    @BeforeEach
    void setUp() {
        activity = new Activity();
        activity.setId(1L);
        activity.setTitle("Yoga");

        activityDTO = new ActivityDTO();
        activityDTO.setId(1L);
        activityDTO.setTitle("Yoga");
    }

    @Test
    void testFindAll() {
        when(activityRepository.findAllByCreatedAsc()).thenReturn(List.of(activity));
        when(modelMapper.map(activity, ActivityDTO.class)).thenReturn(activityDTO);

        List<ActivityDTO> result = activityService.findAll();

        assertEquals(1, result.size());
        assertEquals(activityDTO, result.getFirst());
    }

    @Test
    void testGetActivityById() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));
        when(modelMapper.map(activity, ActivityDTO.class)).thenReturn(activityDTO);

        ActivityDTO result = activityService.getActivityById(1L);

        assertEquals(activityDTO, result);
    }

    @Test
    void testGetActivityByIdNotFound() {
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> activityService.getActivityById(1L));
    }

    @Test
    void testUpdateActivityById() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));
        when(modelMapper.map(activityDTO, Activity.class)).thenReturn(activity);
        when(activityRepository.save(activity)).thenReturn(activity);
        when(modelMapper.map(activity, ActivityDTO.class)).thenReturn(activityDTO);

        ActivityDTO result = activityService.updateActivityById(1L, activityDTO);

        assertEquals(activityDTO, result);
    }

    @Test
    void testUpdateActivityByIdNotFound() {
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> activityService.updateActivityById(1L, activityDTO));
    }

    @Test
    void testCreateActivity() {
        when(modelMapper.map(activityDTO, Activity.class)).thenReturn(activity);
        when(activityRepository.save(activity)).thenReturn(activity);
        when(modelMapper.map(activity, ActivityDTO.class)).thenReturn(activityDTO);

        ActivityDTO result = activityService.createActivity(activityDTO);

        assertEquals(activityDTO, result);
    }

    @Test
    void testDeleteActivityById() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));

        activityService.deleteActivityById(1L);

        verify(activityRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteActivityByIdNotFound() {
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> activityService.deleteActivityById(1L));
    }
}