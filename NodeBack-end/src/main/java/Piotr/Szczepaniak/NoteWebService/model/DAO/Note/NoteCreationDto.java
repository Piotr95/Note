package Piotr.Szczepaniak.NoteWebService.model.DAO.Note;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteCreationDto {
    @NotBlank(message = "Title cannot be empty!")
    @Size(message = "Title should be between 4 and 100 characters long.", min = 4, max = 100)
    private String title;

    @NotBlank(message = "Content cannot be empty!")
    @Size(message = "Title should be between 5 and 10000 characters long.", min = 5, max = 10000)
    private String content;

    @JsonIgnore
    private final LocalDateTime created = LocalDateTime.now();

    @JsonIgnore
    private final LocalDateTime modified = LocalDateTime.now();
}
