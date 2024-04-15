package com.jason.publicaciones.persistence;

import java.util.List;
import java.util.Optional;

import com.jason.publicaciones.model.Comentario;

public interface ComentarioDAO {
    
    List<Comentario> getAllComentarios();
    Optional<Comentario> getComentarioById(Long id);
    Comentario updateComentario(Long id, Comentario comentario);
    void createComentario(Comentario comentario);
    void deleteComentario(Long id);    
}
