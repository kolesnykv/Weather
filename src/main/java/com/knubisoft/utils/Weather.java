package com.knubisoft.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



import java.time.LocalTime;


@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Weather {
    private int cloud_pct;
    private int temp;
    private int feels_like;
    private int min_temp;
    private int max_temp;
    private double wind_speed;
    private LocalTime sunrise;
    private LocalTime sunset;

}
