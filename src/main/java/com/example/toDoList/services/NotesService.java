package com.example.toDoList.services;


import com.example.toDoList.models.Note;
import com.example.toDoList.repositories.NotesRepository;
import com.example.toDoList.util.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NotesService {

    private final NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Note> findAll() {
        return notesRepository.findAll();
    }

    public Note findOne(int id) {
        Optional<Note> foundNote = notesRepository.findById(id);
        return foundNote.orElseThrow(NoteNotFoundException::new);
    }

    @Transactional
    public void save(Note note) {
        notesRepository.save(note);
    }
}
