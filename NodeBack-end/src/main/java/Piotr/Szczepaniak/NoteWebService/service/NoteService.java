package Piotr.Szczepaniak.NoteWebService.service;

import Piotr.Szczepaniak.NoteWebService.exeption.ResourceNotFoundException;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteCreationDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteDetailsDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteListDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteUpdateDto;
import Piotr.Szczepaniak.NoteWebService.model.converter.NoteDtoConverter;
import Piotr.Szczepaniak.NoteWebService.repository.NoteRepository;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRepository;
    private NoteDtoConverter noteDtoConverter;

    public List<NoteListDto> findAll() {
        return Lists.newArrayList(noteRepository.findAll()).stream().map(noteDtoConverter::list).collect(Collectors.toList());
    }

    public NoteDetailsDto get(Long id) {
        return noteDtoConverter.details(noteRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public NoteDetailsDto create(NoteCreationDto note) {
        return noteDtoConverter.details(noteRepository.save(noteDtoConverter.creation(note)));
    }


    public NoteDetailsDto update(NoteUpdateDto note) {
        if (noteRepository.existsById(note.getId())) {
            return noteDtoConverter.details(noteRepository.save(noteDtoConverter.update(note)));
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public void delete(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
