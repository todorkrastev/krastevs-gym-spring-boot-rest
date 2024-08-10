package com.todorkrastev.krastevsgymrest.service;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityCreateDTO;
import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
    List<ActivityDTO> findAll();

    ActivityDTO getActivityById(Long activityId);

    ActivityDTO updateActivityById(Long activityId, ActivityDTO updateActivity);

    ActivityCreateDTO createActivity(ActivityCreateDTO activityCreateDTO);

    void deleteActivityById(Long activityId);

    Boolean doesTitleExist(String title);

    boolean isTitleUnique(String title, Long id);
}
