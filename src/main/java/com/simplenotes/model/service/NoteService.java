package com.simplenotes.model.service;

import com.simplenotes.model.entity.NoteEntity;
import com.simplenotes.model.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteEntity getNoteById(Long id) {
        return noteRepository.getReferenceById(id);
    }
}
