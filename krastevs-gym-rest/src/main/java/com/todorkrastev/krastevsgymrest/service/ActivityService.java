package com.todorkrastev.krastevsgymrest.service;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
    List<ActivityDTO> findAll();

    ActivityDTO getActivityById(Long activityId);

    ActivityDTO updateActivityById(Long activityId, ActivityDTO updateActivity);

    ActivityDTO createActivity(ActivityDTO newActivity);

    void deleteActivityById(Long activityId);

    Boolean doesTitleExist(String title);
}
