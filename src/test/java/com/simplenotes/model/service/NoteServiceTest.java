package com.simplenotes.model.service;

import com.simplenotes.anotation.IT;
import com.simplenotes.database.IntegrationTestDatabase;
import com.simplenotes.model.entity.NoteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;


@IT
public class NoteServiceTest extends IntegrationTestDatabase {
    @Autowired
    private NoteService noteService;

    @Test
    public void getNoteByIdTest() {
        NoteEntity noteById = noteService.getNoteById(1L);

        Long actual = noteById.getId();
        Long expected = 1L;

        Assertions.assertEquals(expected, actual);
    }
}

