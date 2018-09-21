package Piotr.Szczepaniak.NoteWebService;

import Piotr.Szczepaniak.NoteWebService.model.entity.Note;
import Piotr.Szczepaniak.NoteWebService.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class NoteWebServiceApplication {

    public static void main(String[] args) throws RuntimeException {
        SpringApplication.run(NoteWebServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner init(NoteRepository noteRepository) {
        return (ingridiants) -> Arrays.asList("note1,note2,note3".split(","))

                .forEach(
                        newnote -> {
                            Note note = new Note();
                            note.setTitle(newnote);
                            note.setContent("content " + newnote);
                            note.setCreated(LocalDateTime.now());
                            note.setModified(LocalDateTime.now());
                            noteRepository.save(note);
                        });
    }
}
