package com.todorkrastev.krastevsgymrest.service;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;

import java.util.Set;

public interface ActivityService {
    Set<ActivityDTO> findAll();

    ActivityDTO getActivityById(Long activityId);

    ActivityDTO updateActivityById(Long activityId, ActivityDTO activityDTO);

    Long createActivity(ActivityDTO newActivity);

    void deleteActivityById(Long activityId);
}
