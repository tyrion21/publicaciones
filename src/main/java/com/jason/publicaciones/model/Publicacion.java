// Publicacion.java
package com.jason.publicaciones.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

@Entity
@Builder
@Table(name = "publicaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String titulo;
    private String contenido;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Comentario> comentarioList = new ArrayList<>();

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Calificacion> calificacionList = new ArrayList<>();


}