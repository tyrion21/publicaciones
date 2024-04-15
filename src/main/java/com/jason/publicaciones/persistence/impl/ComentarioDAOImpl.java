package com.jason.publicaciones.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jason.publicaciones.model.Comentario;
import com.jason.publicaciones.persistence.ComentarioDAO;
import com.jason.publicaciones.repositories.ComentarioRepository;

@Component
public class ComentarioDAOImpl implements ComentarioDAO {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> getAllComentarios() {
        return (List<Comentario>) comentarioRepository.findAll();
    }

    @Override
    public Optional<Comentario> getComentarioById(Long id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public Comentario updateComentario(Long id, Comentario comentario) {
        if (comentarioRepository.existsById(id)) {
            comentario.setId(id);
            return comentarioRepository.save(comentario);
        } else {
            return null;
        }
    }

    @Override
    public void createComentario(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }
    
}
