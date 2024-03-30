package com.simplenotes.view.note;

import com.simplenotes.model.dto.note.NoteCreationDto;
import com.simplenotes.model.events.note.NoteCreationEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;


@Route("create-note")
@PageTitle("Note creation")
@RolesAllowed({"USER"})
public class CreateNoteView extends VerticalLayout {
    private final ApplicationEventPublisher publisher;
    TextField title = new TextField("Note title:");
    TextArea content = new TextArea("Note content");

    FormLayout layout = new FormLayout(title, content);



    private CreateNoteView(ApplicationEventPublisher publisher) {
        this.publisher = publisher;

        Div container = new Div();
        container.add(layout);
        container.add(createNoteButton(getBinder()));
    }


    private Binder<NoteCreationDto> getBinder() {
        Binder<NoteCreationDto> binder = new Binder<>(NoteCreationDto.class);
        binder.forField(title)
                .asRequired()
                .withValidator(t -> !t.isEmpty() && !t.isBlank(), "Title cannot be blank")
                .withValidator(t -> t.length() >= 127, "Title cannot be  more than 127 characters")
                .withValidator(t -> t.length() < 2, "Title cannot be less than 2 characters")
                .bind(NoteCreationDto::getTitle, NoteCreationDto::setTitle);

        binder.forField(content)
                .asRequired()
                .withValidator(t -> !t.isEmpty() && !t.isBlank(), "Title cannot be blank")
                .withValidator(t -> t.length() >= 127, "Note content cannot be  more than 2047 characters")
                .withValidator(t -> t.length() < 2, "Note content cannot be less than 2 characters")
                .bind(NoteCreationDto::getContent, NoteCreationDto::setContent);

        return null;
    }

    private Button createNoteButton(Binder<NoteCreationDto> binder) {
        return new Button("Save note", e -> {
            try {
                binder.writeBean(
                        new NoteCreationDto()
                );
                publisher.publishEvent(new NoteCreationEvent(NoteCreationDto
                        .builder()
                        .user(SecurityContextHolder.getContext().getAuthentication().getName())
                        .content(content.getValue())
                        .title(title.getTitle())
                        .build()));
                getUI().ifPresent(ui -> ui.navigate("notes"));
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });

    }

}
