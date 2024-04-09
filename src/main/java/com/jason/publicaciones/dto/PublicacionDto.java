package com.jason.publicaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PublicacionDto {

    @NotBlank
    private String titulo;

    @NotNull
    private String contenido;

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }
    
}
