package com.mehedy.journal_app.service;

import com.mehedy.journal_app.api.response.WeatherResponse;
import com.mehedy.journal_app.appcache.AppCache;
import com.mehedy.journal_app.constants.Placeholders;
import com.mehedy.journal_app.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY.getPlaceholder(),city).replace(Placeholders.API_KEY.getPlaceholder(),apiKey);
       ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
