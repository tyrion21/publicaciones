package com.jason.publicaciones.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.publicaciones.model.Calificacion;
import com.jason.publicaciones.persistence.CalificacionDAO;
import com.jason.publicaciones.services.CalificacionService;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    private CalificacionDAO CalificacionDAO;

    @Override
    public List<Calificacion> getAllCalificaciones() {
        return CalificacionDAO.getAllCalificaciones();
    }

    @Override
    public Optional<Calificacion> getCalificacionById(Long id) {
        return CalificacionDAO.getCalificacionById(id);
    }

    @Override
    public void createCalificacion(Calificacion calificacion) {
        CalificacionDAO.createCalificacion(calificacion);
    }

    @Override
    public Calificacion updateCalificacion(Long id, Calificacion calificacion) {
        return CalificacionDAO.updateCalificacion(id, calificacion);
    }

    @Override
    public void deleteCalificacion(Long id) {
        CalificacionDAO.deleteCalificacion(id);
    }

    @Override
    public Double findAverageByPublicacionId(Long publicacionId) {
        return CalificacionDAO.findAverageByPublicacionId(publicacionId);
    }


}
