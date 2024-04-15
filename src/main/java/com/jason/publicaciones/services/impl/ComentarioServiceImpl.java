package com.jason.publicaciones.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.publicaciones.model.Comentario;
import com.jason.publicaciones.persistence.ComentarioDAO;
import com.jason.publicaciones.services.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    private ComentarioDAO comentarioDAO;

    @Override
    public List<Comentario> getAllComentarios() {
        return comentarioDAO.getAllComentarios();
    }

    @Override
    public Optional<Comentario> getComentarioById (Long id) {
        return comentarioDAO.getComentarioById(id);
    }

    @Override
    public void createComentario(Comentario comentario) {
        comentarioDAO.createComentario(comentario);
    }

    @Override
    public Comentario updateComentario(Long id, Comentario comentario) {
        return comentarioDAO.updateComentario(id, comentario);
    }

    @Override
    public void deleteComentario(Long id) {
        comentarioDAO.deleteComentario(id);
    }
    
}
