package com.todorkrastev.krastevsgymrest.web;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityCreateDTO;
import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.service.ActivityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivityDTO>> getAllActivities(@AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails != null) {
            LOGGER.info("User {} is requesting all activities", userDetails.getUsername());
            LOGGER.info("User {} has roles: {}", userDetails.getUsername(), userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));
        } else {
            LOGGER.info("Anonymous user is requesting all activities");
        }

        List<ActivityDTO> all = this.activityService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long activityId) {
        return ResponseEntity.ok(this.activityService.getActivityById(activityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivityById(@PathVariable("id") Long activityId, @Valid @RequestBody ActivityDTO updateActivity) {
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
    public ResponseEntity<ActivityCreateDTO> createActivity(@Valid @RequestBody ActivityCreateDTO activityCreateDTO) {
        ActivityCreateDTO activityDTO = this.activityService.createActivity(activityCreateDTO);

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
