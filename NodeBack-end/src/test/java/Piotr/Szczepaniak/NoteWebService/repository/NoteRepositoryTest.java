package Piotr.Szczepaniak.NoteWebService.repository;

import Piotr.Szczepaniak.NoteWebService.model.entity.Note;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@SpringBootTest
@RunWith(SpringRunner.class)
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;


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
    public void getById() {
        Note Note = noteRepository.findById(2L).get();

        assertThat(Note.getId(), is(2L));
        assertThat(Note.getTitle(), is("note2"));


    }

    @Test
    public void getAll() {


        List<Note> Notes = Lists.newArrayList(noteRepository.findAll());

        assertThat(Notes.size(), is(3));

    }


    @Test

    public void create() {


       Note note = noteRepository.save(createTestNote(1L));


        assertThat(note.getId(), is(1L));
        assertThat(note.getTitle(), is("Title no 1"));


    }

    @Test
    public void update() {
        Note note = createTestNote(1L);
        note.setTitle("Note no 2");
        Note updatedNote = noteRepository.save(note);

        assertThat(updatedNote.getId(), is(1L));
        assertThat(updatedNote.getTitle(), is("Note no 2"));


    }

}

