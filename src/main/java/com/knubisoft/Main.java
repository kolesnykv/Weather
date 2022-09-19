package com.knubisoft;

import com.knubisoft.utils.SimpleWeather;
import com.knubisoft.utils.WeatherPrinter;
import com.knubisoft.utils.foreca.ForecaAPI;
import com.knubisoft.utils.ninjas.NinjasAPI;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //TODO Possible weatherAPI`s below that can be connected

//        Request request2 = new Request.Builder()
//                .url("https://yahoo-weather5.p.rapidapi.com/weather?location=Kyiv&format=json&u=c")
//                .get()
//                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
//                .addHeader("X-RapidAPI-Host", "yahoo-weather5.p.rapidapi.com")
//                .build();
//
//        Response response2 = client.newCall(request2).execute();
//        Request request3 = new Request.Builder()
//                .url("https://weatherapi-com.p.rapidapi.com/current.json?q=50.4501%2C30.5234")
//                .get()
//                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
//                .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
//                .build();
//
//        Response response3 = client.newCall(request3).execute();
        String city = "Kyiv";
        ForecaAPI forecaAPI = new ForecaAPI();
        NinjasAPI ninjasAPI = new NinjasAPI();
        List<SimpleWeather> weatherList = new ArrayList<>();
        weatherList.add(forecaAPI.transform(forecaAPI.callForecaAPI(city)));
        weatherList.add(ninjasAPI.transform(ninjasAPI.callNinjasAPI(city)));
        new WeatherPrinter().printWeather(weatherList);
    }
}
