package com.gustavooarantes.authtodo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Missing username")
    private String username;

    @NotBlank(message = "Missing email")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Missing password")
    private String password;
}
