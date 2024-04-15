package com.jason.publicaciones.services;

import java.util.List;
import java.util.Optional;

import com.jason.publicaciones.model.Publicacion;

public interface PublicacionService {
    
    List<Publicacion> getAllPublicacion();    
    Optional<Publicacion> getPublicacionById(Long id);
    void createPublicacion(Publicacion publicacion);
    Publicacion updatePublicacion(Long id, Publicacion publicacion);
    void deletePublicacion(Long id);
}

