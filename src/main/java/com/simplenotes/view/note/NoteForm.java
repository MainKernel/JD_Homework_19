package com.simplenotes.view.note;


import com.simplenotes.model.dto.NoteDto;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;


public class NoteForm extends FormLayout {
    BeanValidationBinder<NoteDto> binder = new BeanValidationBinder<>(NoteDto.class);
    TextField title = new TextField("Note title");
    TextField content = new TextField("Note content");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");


    public NoteForm() {
        addClassName("note-form");
        add(
                title,
                content,
                createButtonLayout()
        );
    }

    public void setNote(NoteDto note) {
        binder.setBean(note);
    }

    private HorizontalLayout createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);


        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(e -> validateAndSave());

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {

        }
    }
}
