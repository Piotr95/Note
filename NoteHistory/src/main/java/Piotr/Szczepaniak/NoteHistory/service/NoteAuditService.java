package Piotr.Szczepaniak.NoteHistory.service;

import Piotr.Szczepaniak.NoteHistory.entity.Note;
import lombok.AllArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class NoteAuditService {

    private EntityManager entityManager;

    @Transactional
    public List getAllNoteVersions(Long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(Note.class, true, false)
                .add(AuditEntity.id().eq(id));
        return query.getResultList();
    }
}
