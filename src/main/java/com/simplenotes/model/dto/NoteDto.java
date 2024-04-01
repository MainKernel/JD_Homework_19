package com.simplenotes.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class NoteDto {
    private Long id;
    private String noteTitle;
    private String noteContent;
    private Long userId;
    private LocalDateTime creationTime;
}
