package com.knubisoft;

import com.knubisoft.utils.SimpleWeather;
import com.knubisoft.utils.WeatherPrinter;
import com.knubisoft.utils.foreca.ForecaAPI;
import com.knubisoft.utils.ninjas.NinjasAPI;

import java.util.ArrayList;
import java.util.List;
/**
 * The SimpleWeather program implements an application that
 * displays current weather to the standard output due to inputted city name.
 *
 *
 *
 * @author  Vladyslav Kolesnyk
 * @version 1.0
 * @since   2022-09-09
 */
public class Main {
    public static void main(String[] args) {
        ForecaAPI forecaAPI = new ForecaAPI("Lviv");
        NinjasAPI ninjasAPI = new NinjasAPI("Odesa");
        List<SimpleWeather> weatherList = new ArrayList<>();
        weatherList.add(forecaAPI.transform(forecaAPI.callForecaAPI()));
        weatherList.add(ninjasAPI.transform(ninjasAPI.callNinjasAPI()));
        new WeatherPrinter().printWeather(weatherList);
    }
}
