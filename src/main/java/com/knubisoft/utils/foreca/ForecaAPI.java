package com.knubisoft.utils.foreca;

import com.knubisoft.utils.SimpleWeather;
import com.knubisoft.utils.parser.JSONParser;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForecaAPI {
    @SneakyThrows
    public ForecaWeather callForecaAPI(String city) {
        OkHttpClient client = new OkHttpClient();
        String id = findCityId(city, client);
        String forecaUrl = String.format("https://foreca-weather.p.rapidapi.com/current/%s", id) + "?alt=0&tempunit=C&windunit=KMH&tz=Europe%2FLondon&lang=en";
        Request request = new Request.Builder()
                .url(forecaUrl)
                .get()
                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
                .addHeader("X-RapidAPI-Host", "foreca-weather.p.rapidapi.com")
                .build();
        Response response = client.newCall(request).execute();
        List<ForecaWeather> weatherList = new JSONParser().readAll(response.body().string(), ForecaWeather.class);
        return weatherList.get(0);
    }

    @SneakyThrows
    public SimpleWeather transform(Object instance) {
        SimpleWeather simpleWeatherInstance = new SimpleWeather();
        simpleWeatherInstance.setApiName(instance.getClass().getSimpleName());
        for (Field f : instance.getClass().getDeclaredFields()) {
            String name = f.getName();
            switch (name) {
                case "temperature" ->
                        simpleWeatherInstance.setTemperature((Integer) getGetterMethod(name, instance).invoke(instance));
                case "feelsLikeTemp" ->
                        simpleWeatherInstance.setFeelsLike((Integer) getGetterMethod(name, instance).invoke(instance));
                case "windSpeed" ->
                        simpleWeatherInstance.setWindSpeed((Double) getGetterMethod(name, instance).invoke(instance));
                case "cloudiness" ->
                        simpleWeatherInstance.setCloudiness((Integer) getGetterMethod(name, instance).invoke(instance));
                case "pressure" ->
                        simpleWeatherInstance.setPressure((Double) getGetterMethod(name, instance).invoke(instance));
                case "relHumidity" ->
                        simpleWeatherInstance.setHumidity((Integer) getGetterMethod(name, instance).invoke(instance));
            }

        }
        return simpleWeatherInstance;
    }

    @SneakyThrows
    private Method getGetterMethod(String name, Object instance) {
        return instance.getClass().getMethod("get" + StringUtils.capitalize(name));
    }

    private static String findCityId(String city, OkHttpClient client) throws IOException {
        String searchCityURL = String.format("https://foreca-weather.p.rapidapi.com/location/search/%s?lang=en", city);
        Request request = new Request.Builder()
                .url(searchCityURL)
                .get()
                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
                .addHeader("X-RapidAPI-Host", "foreca-weather.p.rapidapi.com")
                .build();
        Response response = client.newCall(request).execute();
        String bodyWithId = response.body().string();
        Pattern pattern = Pattern.compile("\"id\":\\d+");
        Matcher matcher = pattern.matcher(bodyWithId);
        matcher.find();
        String id = matcher.group().split(":")[1];
        return id;
    }
}
