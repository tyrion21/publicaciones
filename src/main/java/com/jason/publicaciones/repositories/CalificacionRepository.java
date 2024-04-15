package com.jason.publicaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jason.publicaciones.model.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long>{

    @Query("SELECT ROUND(AVG(c.valor),2) FROM Calificacion c WHERE c.publicacion.id = :publicacionId")
    Double findAverageByPublicacionId(Long publicacionId);
}