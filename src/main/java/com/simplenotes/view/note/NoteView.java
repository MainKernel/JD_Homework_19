package com.simplenotes.view.note;

import com.simplenotes.model.dto.NoteDto;
import com.simplenotes.model.service.NoteService;
import com.simplenotes.view.main.MainLayout;
import com.simplenotes.view.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = "notes", layout = MainLayout.class)
@PageTitle("Notes")
@PermitAll
public class NoteView extends VerticalLayout {
    private final NoteService noteService;
    private final Grid<NoteDto> grid = new Grid<>(NoteDto.class);

    NoteForm form;

    public NoteView(NoteService noteService) {
        this.noteService = noteService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
    }

    private void updateList() {
        grid.setItems(noteService.getNotes(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    private void configureGrid() {
        grid.addClassName("notes-grid");
        grid.setSizeFull();
        grid.setColumns("noteTitle", "noteContent", "creationTime");
    }

    private Component getContent() {
        configureGrid();
        HorizontalLayout layout = new HorizontalLayout(grid, form);
        layout.setFlexGrow(2, grid);
        layout.setFlexGrow(1, form);
        layout.addClassNames("content");
        layout.addClassName("content");
        layout.setSizeFull();
        return layout;
    }

    private void configureForm() {
        form = new NoteForm();
        form.setWidth("25em");

    }

    private Component getToolbar() {

        Button addContactButton = new Button("Add Note");
        addContactButton.addClickListener(click -> click.getSource().getUI().ifPresent(ui -> ui.navigate("create-note")));
        var toolbar = new HorizontalLayout(addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }



}
