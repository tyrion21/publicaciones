// Calificacion.java
package com.jason.publicaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "calificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    @JsonIgnore
    private Publicacion publicacion;

}
