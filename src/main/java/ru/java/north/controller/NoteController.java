package ru.java.north.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.north.entity.Note;
import ru.java.north.service.NoteService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.getNotes();
        model.addAttribute("notes", notes);
        return "note-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Note());
        return "note-form-create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Note note = noteService.getNote(id);
        model.addAttribute("note", note);
        return "note-form-edit";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute Note note) {
        noteService.createNote(note);
        return "redirect:/api/notes";
    }

    @PostMapping("/edit")
    public String updateNote(@ModelAttribute Note note) {
        noteService.editNote(note);
        return "redirect:/api/notes";
    }

    @GetMapping("/{id}")
    public String getNoteById(@PathVariable Long id, Model model) {
        Note note = noteService.getNote(id);
        model.addAttribute("note", note);
        return "note-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/api/notes";
    }
}
