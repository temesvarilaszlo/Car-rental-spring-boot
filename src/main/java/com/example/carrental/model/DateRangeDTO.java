package com.example.carrental.model;

import com.example.carrental.validator.dateRange.ValidDateRange;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@ValidDateRange
public class DateRangeDTO implements DateRange{
    @NotNull
    @FutureOrPresent
    private final LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private final LocalDate endDate;

    public DateRangeDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }
}
