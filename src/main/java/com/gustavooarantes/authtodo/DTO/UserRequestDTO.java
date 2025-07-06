package com.gustavooarantes.authtodo.DTO;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
}
