package com.knubisoft.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@NoArgsConstructor
@Getter
@AllArgsConstructor

public class OpenWeather {
    private int temp;
    private int feels_like;
    private int temp_min;
    private int temp_max;
    private LocalTime sunrise;
    private LocalTime sunset;
}
