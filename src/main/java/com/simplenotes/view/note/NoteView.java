package com.simplenotes.view.note;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route("notes")
@PageTitle("Simple Notes")
@RolesAllowed({"USER", "ADMIN"})
public class NoteView extends Component {

    public NoteView() {

    }

}
