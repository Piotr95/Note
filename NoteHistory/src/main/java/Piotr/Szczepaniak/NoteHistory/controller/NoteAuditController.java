package Piotr.Szczepaniak.NoteHistory.controller;

import Piotr.Szczepaniak.NoteHistory.service.NoteAuditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@AllArgsConstructor
public class NoteAuditController {
    private NoteAuditService noteAuditService;

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List get(@PathVariable("id") Long id) {
        return noteAuditService.getAllNoteVersions(id);
    }
}
