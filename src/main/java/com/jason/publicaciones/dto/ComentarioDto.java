package com.jason.publicaciones.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioDto {
    
    @NotBlank
    private String contenido;

    public String getContenido() {
        return contenido;
    }
}
