package com.simplenotes.model.liteners;

import com.simplenotes.model.dto.note.NoteCreationDto;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NoteCreationListener {

    @EventListener
    public void accept(NoteCreationDto creationDto) {

    }
}
