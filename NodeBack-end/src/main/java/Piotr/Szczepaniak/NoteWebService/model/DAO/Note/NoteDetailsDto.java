package Piotr.Szczepaniak.NoteWebService.model.DAO.Note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDetailsDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
}
