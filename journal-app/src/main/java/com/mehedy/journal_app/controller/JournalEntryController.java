package com.mehedy.journal_app.controller;

import com.mehedy.journal_app.entity.JournalEntry;
import com.mehedy.journal_app.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    private final JournalEntryService journalEntryService;
    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAlljournals()
    {
        List<JournalEntry> journalEntries=journalEntryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(journalEntries);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry)
    {
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(Optional.of(entry));
            return ResponseEntity.status(HttpStatus.CREATED).body(entry);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entry);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId)
    {
       Optional<JournalEntry> journalEntry= journalEntryService.findById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry)
    {
        Optional<JournalEntry> journalEntry= journalEntryService.findById(id);


        if(journalEntry.isPresent())
        {
            journalEntry.get().setTitle(newEntry.getTitle()!=null && newEntry.getTitle().isEmpty() ?newEntry.getTitle():journalEntry.get().getTitle());
            journalEntry.get().setContent(newEntry.getContent()!=null && newEntry.getContent().isEmpty() ?newEntry.getContent():journalEntry.get().getContent());
            journalEntryService.saveEntry(journalEntry);
            return ResponseEntity.status(HttpStatus.OK).body(journalEntry.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id)
    {
        journalEntryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
