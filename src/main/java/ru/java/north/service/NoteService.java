package ru.java.north.service;

import ru.java.north.entity.Note;

import java.util.List;

public interface NoteService {
    Long createNote(Note note);

    Note editNote(Note note);

    List<Note> createNotes(List<Note> notes);

    List<Note> getNotes();

    void deleteNote(Long id);

    Note getNote(Long id);
}
