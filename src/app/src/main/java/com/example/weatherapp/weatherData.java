package com.example.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {

    private String mTemperature, micon, mcity, mWeatherType;
    private int mCondition;

    public static weatherData fromJson(JSONObject jsonObject) {
        try {
            weatherData weatherD = new weatherData();
            weatherD.mcity = jsonObject.getString("name");
            weatherD.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherD.mWeatherType = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherD.micon = updateWeatherIcon(weatherD.mCondition);
            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
            int roundedValue = (int)Math.rint(tempResult);
            weatherD.mTemperature = Integer.toString(roundedValue);
            return weatherD;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // conditions from https://openweathermap.org/weather-conditions/
    private static String updateWeatherIcon(int condition) {
        if (condition >= 200 && condition <= 232) {
            return "thunderstrom";
        }
        else if (condition >= 300 && condition <= 321) {
            return "lightrain";
        }
        else if (condition >= 500 && condition <= 531) {
            return "rainy";
        }
        else if (condition >= 600 && condition <= 622) {
            return "snow";
        }
        else if (condition >= 701 && condition <= 781) {
            return "fog";
        }
        else if (condition == 800) {
            return "sunny";
        }
        else if (condition >= 801 && condition <= 804) {
            return "cloudy";
        }
        return "sunny";
    }

    public String getmTemperature() {
        return mTemperature + "°C";
    }

    public String getMicon() {
        return micon;
    }

    public String getMcity() {
        return mcity;
    }

    public String getmWeatherType() {
        return mWeatherType;
    }
}