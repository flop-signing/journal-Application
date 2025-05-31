package com.mehedy.journal_app.service;

import com.mehedy.journal_app.entity.JournalEntry;
import com.mehedy.journal_app.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    public JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    public void saveEntry(Optional<JournalEntry> journalEntry) {
        journalEntryRepository.save(journalEntry.orElseThrow());
    }



    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }


    public void deleteById(ObjectId id)
    {
        journalEntryRepository.deleteById(id);
    }

}
