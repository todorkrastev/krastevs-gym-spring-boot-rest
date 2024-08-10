package com.todorkrastev.krastevsgymrest.validation.validator;

import com.todorkrastev.krastevsgymrest.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgymrest.service.ActivityService;
import com.todorkrastev.krastevsgymrest.validation.annotation.UniqueTitle;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, ActivityDTO> {

    @Autowired
    private ActivityService activityService;

    @Override
    public void initialize(UniqueTitle constraintAnnotation) {
    }

    @Override
    public boolean isValid(ActivityDTO activityDTO, ConstraintValidatorContext context) {
        if (activityDTO.getId() == null) {
            return true;
        }
        return activityService.isTitleUnique(activityDTO.getTitle(), activityDTO.getId());
    }
}
