package ru.java.north.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.java.north.entity.Note;
import ru.java.north.repository.NoteRepository;

@Component
public class NoteInit {
    @Bean
    CommandLineRunner init(NoteRepository noteRepository) {
        return args -> {
            Note initialNote = new Note();
            initialNote.setText("Привет, это первая заметка!");
            noteRepository.save(initialNote);
        };
    }
}
