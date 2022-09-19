package com.knubisoft.utils.ninjas;

import com.knubisoft.utils.SimpleWeather;
import com.knubisoft.utils.parser.JSONParser;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.List;

public class NinjasAPI {
    @SneakyThrows
    public NinjasWeather callNinjasAPI(String city) {
        OkHttpClient client = new OkHttpClient();
        String ninjaURL = String.format("https://weather-by-api-ninjas.p.rapidapi.com/v1/weather?&city=%s", city);

        Request request = new Request.Builder()
                .url(ninjaURL)
                .get()
                .addHeader("X-RapidAPI-Key", "5ae9326a0amsh2ae7ea8a449b30dp18c5b6jsn1e7c44bd1e65")
                .addHeader("X-RapidAPI-Host", "weather-by-api-ninjas.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        List<NinjasWeather> weatherList = new JSONParser().readAll("[" + response.body().string() + "]", NinjasWeather.class);
        return weatherList.get(0);

    }

    @SneakyThrows
    public SimpleWeather transform(Object instance) {
        SimpleWeather simpleWeatherInstance = new SimpleWeather();
        simpleWeatherInstance.setApiName(instance.getClass().getSimpleName());
        for (Field f : instance.getClass().getDeclaredFields()) {
            String name = f.getName();
            switch (name) {
                case "temp" ->
                        simpleWeatherInstance.setTemperature((Integer) getGetterMethod(name, instance).invoke(instance));
                case "feels_like" ->
                        simpleWeatherInstance.setFeelsLike((Integer) getGetterMethod(name, instance).invoke(instance));
                case "wind_speed" ->
                        simpleWeatherInstance.setWindSpeed((Double) getGetterMethod(name, instance).invoke(instance));
                case "cloud_pct" ->
                        simpleWeatherInstance.setCloudiness((Integer) getGetterMethod(name, instance).invoke(instance));
                case "min_temp" ->
                        simpleWeatherInstance.setMinimalTemperature((Integer) getGetterMethod(name, instance).invoke(instance));
                case "max_temp" ->
                        simpleWeatherInstance.setMaximumTemperature((Integer) getGetterMethod(name, instance).invoke(instance));
                case "humidity" ->
                        simpleWeatherInstance.setHumidity((Integer) getGetterMethod(name, instance).invoke(instance));
                case "sunrise" ->
                        simpleWeatherInstance.setSunrise((LocalTime) getGetterMethod(name, instance).invoke(instance));
                case "sunset" ->
                        simpleWeatherInstance.setSunset((LocalTime) getGetterMethod(name, instance).invoke(instance));
            }

        }
        return simpleWeatherInstance;
    }

    @SneakyThrows
    private Method getGetterMethod(String name, Object instance) {
        return instance.getClass().getMethod("get" + StringUtils.capitalize(name));
    }
}
