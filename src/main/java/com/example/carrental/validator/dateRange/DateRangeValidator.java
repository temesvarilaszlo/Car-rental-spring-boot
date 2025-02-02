package com.example.carrental.validator.dateRange;


import com.example.carrental.model.DateRange;
import com.example.carrental.model.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateRange> {
    @Override
    public boolean isValid(DateRange dateRange, ConstraintValidatorContext constraintValidatorContext) {
        if (dateRange.getStartDate() == null || dateRange.getEndDate() == null) return true;

        return dateRange.getStartDate().isBefore(dateRange.getEndDate()) ||
                dateRange.getStartDate().isEqual(dateRange.getEndDate());
    }
}
