package com.simplenotes.view.regestration;


import com.simplenotes.model.dto.UserDto;
import com.simplenotes.model.dto.registration.RegistrationFormDto;
import com.simplenotes.model.events.regestration.UserRegisteredEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.context.ApplicationEventPublisher;

@Route("registration")
@PageTitle("Registration")
@AnonymousAllowed
public class RegistrationView extends VerticalLayout{
    private final ApplicationEventPublisher publisher;
    TextField username = new TextField("Username", "JohnD");
    TextField firstName = new TextField("First name", "John");
    TextField lastName = new TextField("Last name", "Doe");
    EmailField emailField = new EmailField("Email", "johnJD@gmail.com");
    TextField phoneNumber = new TextField("Phone number", "+30674613544");
    PasswordField passwordField = new PasswordField("Password", "F32Avm3#1$Nj123");

    FormLayout formLayout = new FormLayout(username, firstName, lastName, emailField, phoneNumber, passwordField);

    public RegistrationView(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        Div container = new Div();
        addClassName("registration-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(new H1("REGISTRATION FORM"));

        formLayout.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("320px", 1),
                new ResponsiveStep("500px", 1));

        container.add(formLayout);


        Button register = registerButton(getFormLayoutBinder());
        register.setWidthFull();
        container.add(register);

        add(container);

    }

    private Binder<UserDto> getFormLayoutBinder() {
        Binder<UserDto> binder = new Binder<>(UserDto.class);
        binder.forField(username)
                .asRequired()
                .withValidator(name -> name.length() >= 2
                        , "Username must contains at least two characters")
                .withValidator(name -> name.length() <= 63,
                        "Maximum length for username 63 characters")
                .bind(UserDto::getUsername, UserDto::setUsername);

        binder.forField(firstName)
                .asRequired()
                .withValidator( n -> n.length() >= 3
                        , "First name must contains at least five characters")
                .withValidator(name -> name.length() <= 63,
                        "Maximum length for first name 63 characters")
                .bind(UserDto::getFirstName, UserDto::setFirstName);

        binder.forField(lastName)
                .asRequired()
                .withValidator( n -> n.length() >= 3
                        , "Last name must contains at least five characters")
                .withValidator(name -> name.length() <= 63,
                        "Maximum length for last name 63 characters")
                .bind(UserDto::getLastName, UserDto::setLastName);
        binder.forField(emailField)
                .asRequired()
                .withValidator(e -> e.matches("^.{4,}@gmail\\.com$"),
                        "Invalid email, must contains at least 4 characters and be in @gmail.com domain")
                .bind(UserDto::getEmail, UserDto::setEmail);

        binder.forField(phoneNumber)
                .asRequired()
                .withValidator(e -> e.matches("^\\+380\\d{9}$"),
                        "Phone number must be Ukrainian")
                .bind(UserDto::getPhoneNumber, UserDto::setPhoneNumber);
        binder.forField(passwordField)
                .asRequired()
                .withValidator(e -> e.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}"),
                        "Password must have at least 8 symbols includes:,\n 0123456789\n A-Z\n a-z\n !@#$%^&*")
                .bind(UserDto::getPassword, UserDto::setPassword);
        return binder;
    }

    private Button registerButton(Binder<UserDto> binder) {
        return new Button("register", event -> {
            try {
                publisher.publishEvent(new UserRegisteredEvent(RegistrationFormDto.builder()
                        .username(username.getValue())
                        .firstName(firstName.getValue())
                        .lastName(lastName.getValue())
                        .email(emailField.getValue())
                        .phoneNumber(phoneNumber.getValue())
                        .password(passwordField.getValue())
                        .build()));
                binder.writeBean(
                        UserDto.builder()
                                .username(username.getValue())
                                .firstName(firstName.getValue())
                                .lastName(lastName.getValue())
                                .email(emailField.getValue())
                                .phoneNumber(phoneNumber.getValue())
                                .password(passwordField.getValue())
                                .build()
                );
                getUI().ifPresent(ui -> ui.navigate("login"));
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });
    }
}
