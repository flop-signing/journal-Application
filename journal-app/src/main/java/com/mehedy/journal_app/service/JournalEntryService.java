package com.mehedy.journal_app.service;

import com.mehedy.journal_app.entity.JournalEntry;
import com.mehedy.journal_app.entity.User;
import com.mehedy.journal_app.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    public JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }

    public void saveEntry(JournalEntry journalEntry, String username) {
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.save(user);
    }

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }


    //    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed = false;

        try {
            User user = userService.findByUserName(username);
            // In no relational db there is no option of cascade, so we delete the data manually.
            removed = user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if (removed) {
                userService.save(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to delete the journal entry", e);
        }

        return removed;
    }

    public List<JournalEntry> findByUsername(String username) {
        return null;
    }

}
