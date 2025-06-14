package com.mehedy.journal_app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;  // This is the type of MongoDB id that is similar when mongodb is created this id.

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;
}
