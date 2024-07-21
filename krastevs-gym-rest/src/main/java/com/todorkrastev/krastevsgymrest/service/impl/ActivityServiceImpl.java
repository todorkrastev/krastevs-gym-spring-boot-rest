package com.todorkrastev.krastevsgymrest.service.impl;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.repository.ActivityRepository;
import com.todorkrastev.krastevsgymrest.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ActivityDTO> findAll() {
        Set<ActivityDTO> test = activityRepository
                .findAllByOrderByIdAsc()
                .stream()
                .map(activity -> modelMapper.map(activity, ActivityDTO.class))
                .collect(Collectors.toSet());

        System.out.println(test);

        return test;
    }

    @Override
    public ActivityDTO getActivityById(Long activityId) {
        return null;
    }

    @Override
    public ActivityDTO updateActivityById(Long activityId, ActivityDTO activityDTO) {
        return null;
    }

    @Override
    public Long createActivity(ActivityDTO newActivity) {
        return 0L;
    }

    @Override
    public void deleteActivityById(Long activityId) {

    }
}
