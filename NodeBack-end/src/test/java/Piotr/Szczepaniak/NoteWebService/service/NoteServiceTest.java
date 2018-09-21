package Piotr.Szczepaniak.NoteWebService.service;

import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteCreationDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteDetailsDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteListDto;
import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteUpdateDto;
import Piotr.Szczepaniak.NoteWebService.model.entity.Note;
import Piotr.Szczepaniak.NoteWebService.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NoteServiceTest {
    @Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    private Iterable<Note> createTestNotesList() {
        List<Note> Notes = new ArrayList<>();

        for (Long i = 0L; i < 10L; i++)
            Notes.add(createTestNote(i));

        return Notes;
    }

    private Note createTestNote(Long id) {
        Note note = new Note();

        note.setId(id);
        note.setTitle("Title no " + id.toString());
        note.setContent("Content no " + id.toString());
        note.setCreated(LocalDateTime.now());
        note.setModified(LocalDateTime.now());
        return note;
    }

    @Test
    public void get() {
        given(noteRepository.findById(1L)).willReturn(Optional.of(createTestNote(1L)));

        NoteDetailsDto Note = noteService.get(1L);

        assertThat(Note.getId(), is(1L));
        assertThat(Note.getTitle(), is("Title no 1"));

        verify(noteRepository, times(1)).findById(1L);

    }

    @Test
    public void findAll() {
        given(noteRepository.findAll()).willReturn(createTestNotesList());

        List<NoteListDto> Notes = noteService.findAll();

        assertThat(Notes.size(), is(10));

        verify(noteRepository, times(1)).findAll();
    }


    @Test
    public void create() {
        given(noteRepository.save(any())).willReturn(createTestNote(1L));

        NoteDetailsDto note = noteService.create(createTestNoteCreationDto(1L));

        assertThat(note.getId(), is(1L));
        assertThat(note.getTitle(), is("Title no 1"));


        //  verify(noteRepository, times(1)).save(any());
        //    verifyNoMoreInteractions(noteRepository);

    }

    private NoteCreationDto createTestNoteCreationDto(long id) {
        return new NoteCreationDto("Title no " + id, "Content no " + id);

    }
}




