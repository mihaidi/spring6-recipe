package com.example.learnspring.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Reservation {

    @NotNull
    @Size(min = 4)
    private String courtName;

    @NotNull
    private LocalDate date;

    @Min(8)
    @Max(22)
    private int hour;

    @NotNull
    private String player;

    @NotNull
    private String sportType;
}
