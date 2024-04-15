package com.jason.publicaciones.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jason.publicaciones.model.Publicacion;
import com.jason.publicaciones.persistence.PublicacionDAO;
import com.jason.publicaciones.repositories.PublicacionRepository;

@Component
public class PublicacionDAOImpl implements PublicacionDAO{

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<Publicacion> getAllPublicaciones() {
        return (List<Publicacion>) publicacionRepository.findAll();
    }

    @Override
    public Optional<Publicacion> getPublicacionById(Long id) {
        return publicacionRepository.findById(id);
    }

    @Override
    public Publicacion updatePublicacion(Long id, Publicacion publicacion) {
        if (publicacionRepository.existsById(id)) {
            publicacion.setId(id);
            return publicacionRepository.save(publicacion);
        } else {
            return null;
        }
    }

    @Override
    public void save(Publicacion publicacion) {
        publicacionRepository.save(publicacion);
    }

    @Override
    public void deleteById(Long id) {
        publicacionRepository.deleteById(id);
    }
    
}
