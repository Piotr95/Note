package Piotr.Szczepaniak.NoteWebService.repository;

import Piotr.Szczepaniak.NoteWebService.model.entity.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}