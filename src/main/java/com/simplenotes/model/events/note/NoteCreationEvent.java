package com.simplenotes.model.events.note;

import com.simplenotes.model.dto.note.NoteCreationDto;
import org.springframework.context.ApplicationEvent;

public class NoteCreationEvent extends ApplicationEvent {
    public NoteCreationEvent(NoteCreationDto creationDto) {
        super(creationDto);
    }
}
