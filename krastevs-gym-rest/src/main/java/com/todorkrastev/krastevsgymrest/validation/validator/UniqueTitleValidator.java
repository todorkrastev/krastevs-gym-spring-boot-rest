package com.todorkrastev.krastevsgymrest.validation.validator;

import com.todorkrastev.krastevsgymrest.service.ActivityService;
import com.todorkrastev.krastevsgymrest.validation.annotation.UniqueTitle;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {
    private final ActivityService activityService;

    public UniqueTitleValidator(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void initialize(UniqueTitle constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return title != null && !activityService.doesTitleExist(title);
    }
}
