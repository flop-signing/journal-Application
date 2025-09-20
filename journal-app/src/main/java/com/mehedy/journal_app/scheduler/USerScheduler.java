package com.mehedy.journal_app.scheduler;

import com.mehedy.journal_app.appcache.AppCache;
import com.mehedy.journal_app.entity.JournalEntry;
import com.mehedy.journal_app.entity.User;
import com.mehedy.journal_app.enums.Sentiment;
import com.mehedy.journal_app.repository.UserRepositoryImpl;
import com.mehedy.journal_app.service.EmailService;
import com.mehedy.journal_app.service.SentimentAnalysis;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class USerScheduler {


    private final EmailService emailService;
    private final UserRepositoryImpl userRepository;
    private final SentimentAnalysis sentimentAnalysis;
    private final AppCache appCache;

    public USerScheduler(EmailService emailService, UserRepositoryImpl userRepository, SentimentAnalysis sentimentAnalysis, AppCache appCache) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.sentimentAnalysis = sentimentAnalysis;
        this.appCache = appCache;
    }

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {

        List<User> users = userRepository.getUserForSentimentAnalysis();

        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {

                emailService.sendEmail(user.getEmail(), "Sentiment for previous week", mostFrequentSentiment.toString());
          /*      SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days " + mostFrequentSentiment).build();
                try{
                    kafkaTemplate.send("weekly-sentiments", sentimentData.getEmail(), sentimentData);
                }catch (Exception e){
                    emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
                }*/
            }
        }




    /*    for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7))).map(JournalEntry::getContent).toList();
            String entry = String.join(" , ", filteredEntries);
            String sentiment = sentimentAnalysis.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment for Last Seven Days.", sentiment);

        }*/
    }

    // Reload the cache in Every Five Minutes

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
