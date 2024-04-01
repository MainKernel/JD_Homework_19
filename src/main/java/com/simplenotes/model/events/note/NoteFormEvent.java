package com.simplenotes.model.events.note;

import com.simplenotes.model.dto.NoteDto;
import com.simplenotes.view.note.NoteForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

@Getter
public abstract class NoteFormEvent extends ComponentEvent<NoteForm> {
    private final NoteDto noteDto;

    public NoteFormEvent(NoteForm source, NoteDto noteDto) {
        super(source, false);
        this.noteDto = noteDto;
    }

    public static class DeleteNoteEvent extends NoteFormEvent{
        public DeleteNoteEvent(NoteForm source, NoteDto noteDto) {
            super(source, noteDto);
        }
    }
    public static class CloseNoteFormEvent extends NoteFormEvent{
        public CloseNoteFormEvent(NoteForm source) {
            super(source, null);
        }
    }

    public static class SaveNoteEvent extends NoteFormEvent{
        public SaveNoteEvent(NoteForm source, NoteDto noteDto) {
            super(source, noteDto);
        }
    }

    public static Registration addDeleteListener(ComponentEventListener<DeleteNoteEvent> listener, Component component) {
        return ComponentUtil.addListener(component,DeleteNoteEvent.class, listener);
    }

    public static Registration addSaveListener(ComponentEventListener<SaveNoteEvent> listener, Component component) {
        return ComponentUtil.addListener(component, SaveNoteEvent.class, listener);
    }

    public static Registration addCloseListener(ComponentEventListener<CloseNoteFormEvent> listener, Component component) {
        return ComponentUtil.addListener(component, CloseNoteFormEvent.class, listener);
    }

}
