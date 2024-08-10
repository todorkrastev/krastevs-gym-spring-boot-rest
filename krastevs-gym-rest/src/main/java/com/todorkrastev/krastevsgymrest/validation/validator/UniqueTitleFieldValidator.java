package com.todorkrastev.krastevsgymrest.validation.validator;

import com.todorkrastev.krastevsgymrest.service.ActivityService;
import com.todorkrastev.krastevsgymrest.validation.annotation.UniqueTitleField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueTitleFieldValidator implements ConstraintValidator<UniqueTitleField, String> {
    private final ActivityService activityService;

    public UniqueTitleFieldValidator(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void initialize(UniqueTitleField constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return title != null && !activityService.doesTitleExist(title);
    }
}
