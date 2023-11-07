package ru.practicum.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateBeforeValidator implements ConstraintValidator<DateBefore, LocalDateTime> {
    private int hours;
    private int days;

    @Override
    public void initialize(DateBefore dateBefore) {
        this.hours = dateBefore.hours();
        this.days = dateBefore.days();
        String message = dateBefore.message();
    }

    public boolean isValid(LocalDateTime date, ConstraintValidatorContext cxt) {
        if (date == null) {
            return true;
        }

        LocalDateTime targetDate = LocalDateTime.now();
        if (hours > 0) {
            targetDate = targetDate.plusHours(hours);
        }
        if (days > 0) {
            targetDate = targetDate.plusDays(days);
        }
        return date.isAfter(targetDate);
    }
}