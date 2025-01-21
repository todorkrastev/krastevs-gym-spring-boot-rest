package com.todorkrastev.krastevsgymrest.web;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityCreateDTO;
import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/activities")
@Tag(
        name = "Activities",
        description = "The controller for managing activities."
)
public class ActivityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all activities",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ActivityDTO.class)
                                    )
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class)
                                    )
                            })
            }
    )
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

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved activity by id",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ActivityDTO.class)
                                    )
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class)
                                    )
                            })

            })
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long activityId) {
        return ResponseEntity.ok(this.activityService.getActivityById(activityId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully updated activity by id",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ActivityDTO.class)
                                    )
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class)
                                    )
                            })
            }
    )
    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
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

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created activity",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ActivityCreateDTO.class)
                                    )
                            }),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class)
                                    )
                            })
            }
    )
    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
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

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted activity by id",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ActivityDTO.class)
                                    )
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class)
                                    )
                            })
            }
    )
    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ActivityDTO> deleteActivityById(@PathVariable("id") Long activityId) {
        this.activityService.deleteActivityById(activityId);
        return ResponseEntity.noContent().build();
    }
}
