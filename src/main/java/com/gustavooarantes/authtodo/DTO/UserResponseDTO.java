package com.gustavooarantes.authtodo.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
