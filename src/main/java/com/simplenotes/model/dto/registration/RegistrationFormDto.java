package com.simplenotes.model.dto.registration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import javax.annotation.RegEx;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationFormDto {
    @NotEmpty
    @Size(min = 5, max = 63)
    private String firstName;
    @NotEmpty
    @Size(min = 5, max = 63)
    private String lastName;
    @NotEmpty
    @Size(min = 2, max = 63)
    private String username;
    @Email(regexp = "^.{4,}@gmail\\.com$")
    private String email;
    @NotEmpty
    @Max(9)
    @Pattern(regexp = "^\\+380\\d{9}$")
    private String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}")
    private String password;
}
