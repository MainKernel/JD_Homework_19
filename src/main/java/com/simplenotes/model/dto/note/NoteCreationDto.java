package com.simplenotes.model.dto.note;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NoteCreationDto {
    @NotEmpty
    private String user;
    @Size(min = 1, max = 127)
    @NotEmpty
    private String title;
    @NotEmpty
    @Size(min = 1, max = 2047)
    private String content;

}
