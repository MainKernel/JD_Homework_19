package com.simplenotes.model.mapper;

import com.simplenotes.anotation.IT;
import com.simplenotes.database.IntegrationTestDatabase;
import com.simplenotes.model.dto.NoteDto;
import com.simplenotes.model.entity.NoteEntity;
import com.simplenotes.model.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@IT
public class NoteMapperTest extends IntegrationTestDatabase {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteMapper mapper;

    @Test
    public void noteEntityToDtoTest() {

        NoteEntity entity = noteRepository.getReferenceById(1L);

        NoteDto dto = mapper.noteEntityToDto(entity);

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getUser().getId(), dto.getUserId());
        Assertions.assertEquals(entity.getContent(), dto.getNoteContent());
        Assertions.assertEquals(entity.getTitle(), dto.getNoteTitle());
        Assertions.assertEquals(entity.getCreatedAt(), dto.getCreationTime());
    }
}