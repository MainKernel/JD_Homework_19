package com.simplenotes.view.note;

import com.simplenotes.model.dto.note.NoteCreationDto;
import com.simplenotes.model.events.note.NoteCreationEvent;
import com.simplenotes.view.main.MainLayout;
import com.simplenotes.view.main.MainView;
import com.vaadin.flow.component.Key;
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
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;


@Route(value = "create-note", layout = MainLayout.class)
@PageTitle("Note creation")
@PermitAll
public class CreateNoteView extends VerticalLayout {
    private final ApplicationEventPublisher publisher;
    TextField title = new TextField("Note title:", "Happy day :)");
    TextArea content = new TextArea("Note content", "Happy note content ))");
    FormLayout layout = new FormLayout(title, content);

    private CreateNoteView(ApplicationEventPublisher publisher) {
        this.publisher = publisher;

        Div container = new Div();
        container.add(layout);
        Button noteButton = createNoteButton(getBinder());
        noteButton.addClickShortcut(Key.SHIFT       );
        container.add(noteButton);
        add(container);
    }


    private Binder<NoteCreationDto> getBinder() {
        Binder<NoteCreationDto> binder = new Binder<>(NoteCreationDto.class);
        binder.forField(title)
                .asRequired()
                .withValidator(t -> !t.isEmpty() && !t.isBlank(), "Title cannot be blank")
                .bind(NoteCreationDto::getTitle, NoteCreationDto::setTitle);
        binder.forField(content)
                .asRequired()
                .withValidator(t -> !t.isEmpty() && !t.isBlank(), "Title cannot be blank")
                .bind(NoteCreationDto::getContent, NoteCreationDto::setContent);
        return binder;
    }

    private Button createNoteButton(Binder<NoteCreationDto> binder) {
        return new Button("Save note", e -> {
            try {
                if (binder.isValid()) {
                    publisher.publishEvent(new NoteCreationEvent(NoteCreationDto
                            .builder()
                            .user(SecurityContextHolder.getContext().getAuthentication().getName())
                            .content(content.getValue())
                            .title(title.getValue())
                            .build())
                    );
                    binder.writeBean(
                            NoteCreationDto
                                    .builder()
                                    .user(SecurityContextHolder.getContext().getAuthentication().getName())
                                    .content(content.getValue())
                                    .title(title.getTitle())
                                    .build()
                    );
                }
                getUI().ifPresent(ui -> ui.navigate(NoteView.class));
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });

    }

}
