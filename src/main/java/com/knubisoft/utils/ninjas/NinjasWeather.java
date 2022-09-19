package com.knubisoft.utils.ninjas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalTime;


@NoArgsConstructor
@Getter
@AllArgsConstructor
public class NinjasWeather {
    private int cloud_pct;
    private int temp;
    private int feels_like;
    private int min_temp;
    private int max_temp;
    private int humidity;
    private double wind_speed;
    private LocalTime sunrise;
    private LocalTime sunset;

}
