package com.simplenotes.model.liteners.note;

import com.simplenotes.model.dto.note.NoteCreationDto;
import com.simplenotes.model.events.note.NoteCreationEvent;
import com.simplenotes.model.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteCreationListener {
   private final NoteService noteService;

    @EventListener
    public void accept(NoteCreationEvent event) {
        noteService.saveCreatedNote((NoteCreationDto) event.getSource());
    }
}
