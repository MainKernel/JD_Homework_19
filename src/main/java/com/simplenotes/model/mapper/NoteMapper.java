package com.simplenotes.model.mapper;

import com.simplenotes.model.dto.NoteDto;
import com.simplenotes.model.dto.note.NoteCreationDto;
import com.simplenotes.model.entity.NoteEntity;
import com.simplenotes.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteDto noteEntityToDto(NoteEntity note) {
        return NoteDto.builder()
                .id(note.getId())
                .userId(note.getUser().getId())
                .noteTitle(note.getTitle())
                .noteContent(note.getContent())
                .creationTime(note.getCreatedAt())
                .build();
    }

    public NoteEntity noteCreationDtoToEntity(UserEntity user, NoteCreationDto dto) {
        return NoteEntity.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
