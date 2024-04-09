package com.jason.publicaciones.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CalificacionDto {

    @PositiveOrZero
    @NotNull
    private double calificacion;

    public double getCalificacion() {
        return calificacion;
    }

    
}
