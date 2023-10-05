package ru.java.north.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.java.north.entity.Note;
import ru.java.north.service.NoteService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class NotesController {

    private final NoteService noteService;

    @PostMapping("/create-note")
    public Long createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @PutMapping("/edit-note")
    public Note updateNote(@RequestBody Note note) {
        return noteService.editNote(note);
    }

    @PostMapping("/create-notes")
    public List<Note> createNotes(@RequestBody List<Note> notes) {
        return noteService.createNotes(notes);
    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteService.getNote(id);
    }

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}
