package com.simplenotes.model.service;

import com.simplenotes.model.dto.NoteDto;
import com.simplenotes.model.dto.note.NoteCreationDto;
import com.simplenotes.model.entity.NoteEntity;
import com.simplenotes.model.entity.UserEntity;
import com.simplenotes.model.mapper.NoteMapper;
import com.simplenotes.model.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final UserService userService;
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteEntity getNoteById(Long id) {
        return noteRepository.getReferenceById(id);
    }

    public void saveCreatedNote(NoteCreationDto dto) {
        UserEntity user = userService.findUserByUsername(dto.getUser());
        NoteEntity entity = noteMapper.noteCreationDtoToEntity(user, dto);
        noteRepository.save(entity);
    }

    public List<NoteDto> getNotes(String username) {
        return noteRepository.getAllByUserUsername(username).stream().map( e -> NoteDto.builder()
                .id(e.getId())
                .userId(e.getUser().getId())
                .noteTitle(e.getTitle())
                .noteContent(e.getContent())
                .creationTime(e.getCreatedAt())
                .build()).toList();
    }
}
