package Piotr.Szczepaniak.NoteWebService.model.converter;

import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteCreationDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteDetailsDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteListDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteUpdateDto;
import Piotr.Szczepaniak.NoteWebService.model.entity.Note;
import Piotr.Szczepaniak.NoteWebService.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@AllArgsConstructor
public class NoteDtoConverter implements DtoConverter<Note, NoteListDto, NoteDetailsDto, NoteCreationDto, NoteUpdateDto> {

    private final ModelMapper modelMapper;
    private final NoteRepository noteRepository;

    @Override
    public Note creation(NoteCreationDto NoteCreationDto) {
        return modelMapper.map(NoteCreationDto, Note.class);
    }

    @Override
    public Note update(NoteUpdateDto noteUpdateDto) {
        Note noteDAO = modelMapper.map(noteUpdateDto, Note.class);
        Note note = noteRepository.findById(noteDAO.getId()).orElseThrow(EntityNotFoundException::new);
        noteDAO.setCreated(note.getCreated());
        return noteDAO;
    }

    @Override
    public NoteListDto list(Note note) {
        return modelMapper.map(note, NoteListDto.class);
    }

    @Override
    public NoteDetailsDto details(Note note) {
        return modelMapper.map(note, NoteDetailsDto.class);
    }
}
