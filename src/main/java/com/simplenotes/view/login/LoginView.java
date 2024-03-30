package com.simplenotes.view.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login page")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginView() {

        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setForgotPasswordButtonVisible(false);

        login.setAction("login");
        login.getElement().getThemeList().add("dark");

        add(new H1("Simple Notes"), login);
        Button registration = new Button("Registration");
        registration.setThemeName("dark");
        registration.addClickListener(e->getUI().ifPresent(ui -> ui.navigate("registration")));
        add(registration);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

}
