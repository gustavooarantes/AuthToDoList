package com.gustavooarantes.authtodo.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {
    private Integer id;
    private String description;
    private boolean status;
    private LocalDateTime createdAt;
}
