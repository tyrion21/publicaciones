package com.jason.publicaciones.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.publicaciones.model.Publicacion;
import com.jason.publicaciones.persistence.PublicacionDAO;
import com.jason.publicaciones.services.PublicacionService;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionDAO publicacionDAO;

    @Override
    @Transactional
    public List<Publicacion> getAllPublicacion() {
        return publicacionDAO.getAllPublicaciones();
    }

    @Override
    public Optional<Publicacion> getPublicacionById(Long id) {
        return publicacionDAO.getPublicacionById(id);
    }

    @Override
    public void createPublicacion(Publicacion publicacion) {
        publicacionDAO.save(publicacion);
    }

    @Override
    public Publicacion updatePublicacion(Long id, Publicacion publicacion) {
        return publicacionDAO.updatePublicacion(id, publicacion);
    }

    @Override
    public void deletePublicacion(Long id) {
        publicacionDAO.deleteById(id);
    }

}
