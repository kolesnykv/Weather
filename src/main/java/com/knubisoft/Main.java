package com.knubisoft;

import com.knubisoft.utils.ForecaWeather;
import com.knubisoft.utils.JSONParser;
import com.knubisoft.utils.OpenWeather;
import com.knubisoft.utils.Weather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

//        Request request = new Request.Builder()
//                .url("https://weather-by-api-ninjas.p.rapidapi.com/v1/weather?lat=50.4501&lon=30.5234&city=Kyiv&country=Ukraine")
//                .get()
//                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
//                .addHeader("X-RapidAPI-Host", "weather-by-api-ninjas.p.rapidapi.com")
//                .build();
//
//        Response response = client.newCall(request).execute();
//
//        Request request1 = new Request.Builder()
//                .url("https://foreca-weather.p.rapidapi.com/observation/latest/100703448?lang=en")
//                .get()
//                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
//                .addHeader("X-RapidAPI-Host", "foreca-weather.p.rapidapi.com")
//                .build();
//
//        Response response1 = client.newCall(request1).execute();
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
        Request request = new Request.Builder()
                .url("https://foreca-weather.p.rapidapi.com/location/search/Kyiv?lang=en")
                .get()
                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
                .addHeader("X-RapidAPI-Host", "foreca-weather.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        Request request2 = new Request.Builder()
                .url("https://foreca-weather.p.rapidapi.com/current/100703448?alt=0&tempunit=C&windunit=KMH&tz=Europe%2FLondon&lang=en")
                .get()
                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
                .addHeader("X-RapidAPI-Host", "foreca-weather.p.rapidapi.com")
                .build();

        Response response2 = client.newCall(request2).execute();

        List<ForecaWeather> weatherList = new JSONParser().readAll(response2.body().string(), ForecaWeather.class);
    }
}
