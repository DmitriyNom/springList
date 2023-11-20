package com.example.toDoList.controllers;

import com.example.toDoList.models.Note;
import com.example.toDoList.services.NotesService;
import com.example.toDoList.util.NoteErrorResponse;
import com.example.toDoList.util.NoteNotCreatedException;
import com.example.toDoList.util.NoteNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping()
    public List<Note> getNotes() {
        return notesService.findAll();
    }

    @GetMapping("/{id}")
    public Note getSimpleNote(@PathVariable("id") int id) {
        return notesService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Note note,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMSG = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error: errors) {
                errorMSG.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";/n");
            }

            throw new NoteNotCreatedException(errorMSG.toString());
        }

        notesService.save(note);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //---------------------------Handlers-----------------------------------

    @ExceptionHandler
    private ResponseEntity<NoteErrorResponse> handleException(NoteNotFoundException e) {
        NoteErrorResponse response = new NoteErrorResponse(
                "Note with this id has not been found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<NoteErrorResponse> handleException(NoteNotCreatedException e) {
        NoteErrorResponse response = new NoteErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
