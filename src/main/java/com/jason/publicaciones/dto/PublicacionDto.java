package com.jason.publicaciones.dto;

import java.util.List;
import java.util.ArrayList;

import com.jason.publicaciones.model.Comentario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PublicacionDto {

    private Long id;
    private String titulo;
    private String contenido;
    @Builder.Default
    private List<Comentario> comentarioList = new ArrayList<>();
}

