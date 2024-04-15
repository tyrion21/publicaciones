package com.jason.publicaciones.persistence;

import java.util.List;
import java.util.Optional;

import com.jason.publicaciones.model.Calificacion;

public interface CalificacionDAO {

    List<Calificacion> getAllCalificaciones();

    Optional<Calificacion> getCalificacionById(Long id);

    Calificacion updateCalificacion(Long id, Calificacion calificacion);

    void createCalificacion(Calificacion calificacion);

    void deleteCalificacion(Long id);

    Double findAverageByPublicacionId(Long publicacionId);
}
