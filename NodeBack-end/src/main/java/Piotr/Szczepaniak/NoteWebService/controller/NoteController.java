package Piotr.Szczepaniak.NoteWebService.controller;


import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteCreationDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteDetailsDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteListDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteUpdateDto;
import Piotr.Szczepaniak.NoteWebService.model.converter.NoteDtoConverter;
import Piotr.Szczepaniak.NoteWebService.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/note")
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    private NoteDtoConverter noteDtoConverter;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<NoteListDto> getAll() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public NoteDetailsDto getById(@PathVariable("id") Long id) {
        return noteService.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public NoteDetailsDto create(@Valid @RequestBody NoteCreationDto note) {
        return noteService.create(note);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public NoteDetailsDto update(@Valid @RequestBody NoteUpdateDto notedao) {
        return noteService.update(notedao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        noteService.delete(id);
    }
}