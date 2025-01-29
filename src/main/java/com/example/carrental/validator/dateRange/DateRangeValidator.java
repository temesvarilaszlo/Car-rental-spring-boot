package com.example.carrental.validator.dateRange;


import com.example.carrental.model.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, ReservationDTO> {
    @Override
    public boolean isValid(ReservationDTO reservationDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (reservationDTO.getStartDate() == null || reservationDTO.getEndDate() == null) return true;

        return reservationDTO.getStartDate().isBefore(reservationDTO.getEndDate()) ||
                reservationDTO.getStartDate().isEqual(reservationDTO.getEndDate());
    }
}
