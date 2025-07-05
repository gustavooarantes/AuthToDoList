package com.gustavooarantes.authtodo.DTO;


import lombok.Data;

@Data
public class TaskRequestDTO {
    private Integer userId;
    private String description;
    private boolean status;
}
