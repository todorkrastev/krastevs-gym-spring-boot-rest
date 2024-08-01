package com.todorkrastev.krastevsgymrest.service.impl;

import com.todorkrastev.krastevsgymrest.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.model.entity.Activity;
import com.todorkrastev.krastevsgymrest.repository.ActivityRepository;
import com.todorkrastev.krastevsgymrest.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ActivityDTO> findAll() {
        return this.activityRepository
                .findAllByCreatedAsc()
                .stream()
                .map(activity -> this.modelMapper.map(activity, ActivityDTO.class))
                .toList();
    }

    @Override
    public ActivityDTO getActivityById(Long activityId) {
        Activity activity = this.activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFoundException("Activity", "id", activityId));

        return this.modelMapper.map(activity, ActivityDTO.class);
    }

    @Override
    public ActivityDTO updateActivityById(Long activityId, ActivityDTO updateActivity) {
        this.activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFoundException("Activity", "id", activityId));

        Activity activity = this.modelMapper.map(updateActivity, Activity.class);
        Activity saved = this.activityRepository.save(activity);

        return this.modelMapper.map(saved, ActivityDTO.class);
    }

    @Override
    public ActivityDTO createActivity(ActivityDTO newActivity) {
        Activity activity = this.modelMapper.map(newActivity, Activity.class);
        Activity saved = this.activityRepository.save(activity);

        return this.modelMapper.map(saved, ActivityDTO.class);
    }

    @Override
    public void deleteActivityById(Long activityId) {
        this.activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFoundException("Activity", "id", activityId));

        this.activityRepository.deleteById(activityId);
    }

    @Override
    public Boolean doesTitleExist(String title) {
        return this.activityRepository.existsByTitle(title);
    }
}
