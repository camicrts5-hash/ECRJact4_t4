package com.example.crud.dto;

import lombok.Data;

@Data
public class EstudianteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}