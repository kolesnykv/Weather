package com.knubisoft.utils.foreca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor

public class ForecaWeather {
    private int temperature;
    private int feelsLikeTemp;
    private double windSpeed;
    private int cloudiness;
    private double pressure;
    private int relHumidity;
}
