package com.simplenotes.model.events.regestration;

import com.simplenotes.model.dto.registration.RegistrationFormDto;
import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(RegistrationFormDto formDto) {
        super(formDto);
    }
}
