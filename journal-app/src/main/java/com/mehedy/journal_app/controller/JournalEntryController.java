package com.mehedy.journal_app.controller;

import com.mehedy.journal_app.entity.JournalEntry;
import com.mehedy.journal_app.entity.User;
import com.mehedy.journal_app.service.JournalEntryService;
import com.mehedy.journal_app.service.UserService;
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
    private final UserService userService;

    public JournalEntryController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService=userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntry>> getAlljournalEntriesOfUser(@PathVariable String username)
    {
        User existingUser = userService.findByUserName(username);
        List<JournalEntry> journalEntries=existingUser.getJournalEntries();

        if(journalEntries!=null && !journalEntries.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.OK).body(journalEntries);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry, @PathVariable String username)
    {
        try {
            journalEntryService.saveEntry(entry,username);
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


    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String username)
    {
        JournalEntry journalEntry= journalEntryService.findById(id).orElse(null);


        if(journalEntry!=null)
        {
            journalEntry.setTitle(newEntry.getTitle()!=null && newEntry.getTitle().isEmpty() ?newEntry.getTitle():journalEntry.getTitle());
            journalEntry.setContent(newEntry.getContent()!=null && newEntry.getContent().isEmpty() ?newEntry.getContent():journalEntry.getContent());
            journalEntryService.saveEntry(journalEntry);
            return ResponseEntity.status(HttpStatus.OK).body(journalEntry);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/id/{username}/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id, @PathVariable String username)
    {
        journalEntryService.deleteById(id,username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
