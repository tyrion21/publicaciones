package com.jason.publicaciones.persistence;

import java.util.Optional;
import java.util.List;

import com.jason.publicaciones.model.Publicacion;

public interface PublicacionDAO {
    
    List<Publicacion> getAllComentario();
    Optional<Publicacion> getPublicacionById(Long id);
    Publicacion updatePublicacion(Long id, Publicacion publicacion);
    void save(Publicacion publicacion);
    void deleteById(Long id);
}
