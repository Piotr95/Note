package Piotr.Szczepaniak.NoteWebService.controller;

import Piotr.Szczepaniak.NoteWebService.model.DAO.Note.NoteDetailsDto;
import Piotr.Szczepaniak.NoteWebService.model.converter.NoteDtoConverter;
import Piotr.Szczepaniak.NoteWebService.model.entity.Note;
import Piotr.Szczepaniak.NoteWebService.repository.NoteRepository;
import Piotr.Szczepaniak.NoteWebService.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)


public class NoteControllerTest {

    private static final String API_NODES = "/api/note";
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private NoteService noteService;
    @MockBean
    private NoteRepository noteRepository;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    private NoteDtoConverter noteDtoConverter;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
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
    public void getByIdTest() throws Exception {
        given(noteService.get(any())).willReturn(createTestNoteDetail(1L, "title no 1", "content  no 1", 0));

        mockMvc.perform(get(API_NODES + "/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title no 1")))
                .andExpect(jsonPath("$.content", is("content  no 1")));


        verify(noteService, times(1)).get(1L);
        verifyNoMoreInteractions(noteService);
    }


    @Test
    public void createTest() {
        NoteDetailsDto creatednote = createTestNoteDetail(1L, "createdTitle", "createdContent", 0);

        given(noteService.update(any())).willReturn(creatednote);
        try {
            mockMvc.perform(post(API_NODES).content(mapper.writeValueAsString(creatednote))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new IllegalStateException();
        }

        verify(noteService, times(1)).create(any());
        verifyNoMoreInteractions(noteService);
    }

    @Test
    public void updateTest() {
        NoteDetailsDto updatednote = createTestNoteDetail(1L, "updatedTitle", "UpdatedContent", 3);

        given(noteService.update(any())).willReturn(updatednote);
        try {
            mockMvc.perform(put(API_NODES).content(mapper.writeValueAsString(updatednote))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title", is("updatedTitle")))
                    .andExpect(jsonPath("$.content", is("UpdatedContent")));
        } catch (Exception e) {
            throw new IllegalStateException();
        }

        verify(noteService, times(1)).update(any());
        verifyNoMoreInteractions(noteService);
    }

    private NoteDetailsDto createTestNoteDetail(long id, String title, String content, int createHoursdelay) {
        return new NoteDetailsDto(id, title,
                content, LocalDateTime.now().
                minusHours(createHoursdelay), LocalDateTime.now());
    }


    @Test
    public void deleteTest() throws Exception {
        doNothing().when(noteService).delete(any(Long.class));

        mockMvc.perform(delete(API_NODES + "/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());

        verify(noteService, times(1)).delete(1L);
        verifyNoMoreInteractions(noteService);
    }
}
