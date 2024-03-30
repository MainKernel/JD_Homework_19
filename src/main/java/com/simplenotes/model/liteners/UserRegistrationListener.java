package com.simplenotes.model.liteners;

import com.simplenotes.model.dto.registration.RegistrationFormDto;
import com.simplenotes.model.events.regestration.UserRegisteredEvent;
import com.simplenotes.model.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationListener {
    private final RegistrationService registrationService;

    @EventListener
    public void accept(UserRegisteredEvent event) {
        registrationService.registerUser((RegistrationFormDto) event.getSource());
    }
}
