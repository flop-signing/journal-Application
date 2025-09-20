package com.mehedy.journal_app.appcache;

import com.mehedy.journal_app.entity.ConfigJournalAppEntity;
import com.mehedy.journal_app.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppCache {
    public Map<String,String> appCache;

    public enum keys{
        WEATHER_API
    }


    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
        List<ConfigJournalAppEntity> configJournalAppEntities = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity : configJournalAppEntities){
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
