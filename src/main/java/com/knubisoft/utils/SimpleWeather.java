package com.knubisoft.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter

public class SimpleWeather {
    String apiName;
    private int temperature;
    private int feelsLike;
    private int minimalTemperature;
    private int maximumTemperature;
    private int humidity;
    private double windSpeed;
    private int cloudiness;
    private double pressure;
    private LocalTime sunrise;
    private LocalTime sunset;
}
