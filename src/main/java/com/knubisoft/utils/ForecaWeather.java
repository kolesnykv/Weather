package com.knubisoft.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor

public class ForecaWeather {
    int temperature;
    int feelsLikeTemp;
    int windSpeed;
    int cloudiness;
    double pressure;
}
