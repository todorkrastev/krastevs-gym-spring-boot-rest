package com.todorkrastev.krastevsgymrest.web;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> all = this.activityService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long activityId) {
        return ResponseEntity.ok(this.activityService.getActivityById(activityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivityById(@PathVariable("id") Long activityId, @RequestBody ActivityDTO updateActivity) {
        ActivityDTO activityDTO = this.activityService.updateActivityById(activityId, updateActivity);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(activityDTO)
                        .toUri()
        ).body(activityDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO newActivity) {
        ActivityDTO activityDTO = this.activityService.createActivity(newActivity);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(activityDTO)
                        .toUri()
        ).body(activityDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActivityDTO> deleteActivityById(@PathVariable("id") Long activityId) {
        this.activityService.deleteActivityById(activityId);
        return ResponseEntity.noContent().build();
    }
}
