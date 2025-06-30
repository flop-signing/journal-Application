package com.mehedy.journal_app.api.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

// Conversion of POJO to JSON is Serialization
// Convert Json To java is called DeSerialization

public class WeatherResponse {
    private Current current;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Current {
        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;

        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;

        // Getters and Setters
        public String getObservationTime() {
            return observationTime;
        }

        public void setObservationTime(String observationTime) {
            this.observationTime = observationTime;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public ArrayList<String> getWeatherDescriptions() {
            return weatherDescriptions;
        }

        public void setWeatherDescriptions(ArrayList<String> weatherDescriptions) {
            this.weatherDescriptions = weatherDescriptions;
        }
    }
}
