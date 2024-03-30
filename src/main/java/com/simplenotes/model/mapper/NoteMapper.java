package com.simplenotes.model.mapper;

import com.simplenotes.model.dto.NoteDto;
import com.simplenotes.model.entity.NoteEntity;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteDto noteEntityToDto(NoteEntity note) {
        return NoteDto.builder()
                .id(note.getId())
                .userId(note.getUser().getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .build();
    }
}
