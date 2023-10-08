package ru.java.north.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.java.north.entity.Note;
import ru.java.north.exception.NotFoundException;
import ru.java.north.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void setUp() {
        noteRepository.deleteAll();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNote() {
        Note note = new Note();
        note.setId(1L);
        when(noteRepository.save(any(Note.class))).thenReturn(note);
        Long id = noteService.createNote(note);
        assertEquals(1L, id);
    }

    @Test
    public void testEditNote() {
        Note existingNote = new Note();
        existingNote.setId(1L);
        existingNote.setText("Original Text");
        Note updatedNote = new Note();
        updatedNote.setId(1L);
        updatedNote.setText("Updated Text");
        when(noteRepository.findById(1L)).thenReturn(Optional.of(existingNote));
        when(noteRepository.save(any(Note.class))).thenReturn(updatedNote);
        Note resultNote = noteService.editNote(updatedNote);
        assertEquals("Updated Text", resultNote.getText());
    }

    @Test
    public void testEditNoteNotFound() {
        Note note = new Note();
        note.setId(1L);
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> noteService.editNote(note));
    }

    @Test
    public void testCreateNotes() {
        List<Note> notes = new ArrayList<>();
        Note note1 = new Note();
        note1.setId(1L);
        notes.add(note1);
        Note note2 = new Note();
        note2.setId(2L);
        notes.add(note2);
        when(noteRepository.saveAll(anyIterable())).thenReturn(notes);
        List<Note> resultNotes = noteService.createNotes(notes);
        assertEquals(2, resultNotes.size());
    }

    @Test
    public void testGetNotes() {
        List<Note> notes = new ArrayList<>();
        Note note1 = new Note();
        note1.setId(1L);
        notes.add(note1);
        Note note2 = new Note();
        note2.setId(2L);
        notes.add(note2);
        when(noteRepository.findAll()).thenReturn(notes);
        List<Note> resultNotes = noteService.getNotes();
        assertEquals(2, resultNotes.size());
    }

    @Test
    public void testDeleteNote() {
        Long id = 1L;
        doNothing().when(noteRepository).deleteById(id);
        noteService.deleteNote(id);
        verify(noteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetNote() {
        Long id = 1L;
        Note note = new Note();
        note.setId(id);
        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        Note resultNote = noteService.getNote(id);
        assertEquals(id, resultNote.getId());
    }

    @Test
    public void testGetNoteNotFound() {
        Long id = 1L;
        when(noteRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> noteService.getNote(id));
    }
}
