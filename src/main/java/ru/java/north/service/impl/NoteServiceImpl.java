package ru.java.north.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.java.north.entity.Note;
import ru.java.north.exception.NotFoundException;
import ru.java.north.repository.NoteRepository;
import ru.java.north.service.NoteService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Long createNote(Note note) {
        log.debug("Create Note {}", note);
        Note saveNote = noteRepository.save(note);
        return saveNote.getId();
    }

    @Override
    public Note editNote(Note note) {
        log.debug("Edit note {}", note);
        return noteRepository.findById(note.getId())
                .map(existingNote -> {
                    existingNote.setText(note.getText());
                    return noteRepository.save(existingNote);
                })
                .orElseThrow(() -> new NotFoundException("Note with the specified ID was not found"));
    }

    @Override
    public List<Note> createNotes(List<Note> notes) {
        return noteRepository.saveAll(notes);
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note getNote(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with the specified ID was not found"));
    }
}
