package Piotr.Szczepaniak.NoteWebService.model.DAO.Note;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoteUpdateDto {
    @NotNull
    private Long id;
    @NotBlank(message = "title cannot be empty!")
    @Size(message = "title should be between 4 and 100 characters long.", min = 4, max = 100)
    private String title;

    @NotBlank(message = "content cannot be empty!")
    @Size(message = "content should be between 5 and 2500 characters long.", min = 5, max = 2500)
    private String content;

    @JsonIgnore
    private final LocalDateTime modified = LocalDateTime.now();
}
