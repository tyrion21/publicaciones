package com.jason.publicaciones.dto;

import com.jason.publicaciones.model.Publicacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CalificacionDto {

    private Long id;
    private double valor;
    private Publicacion publicacionId;

}
