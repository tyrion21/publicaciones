package com.jason.publicaciones.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jason.publicaciones.model.Calificacion;
import com.jason.publicaciones.persistence.CalificacionDAO;
import com.jason.publicaciones.repositories.CalificacionRepository;

@Component
public class CalificacionDAOImpl implements CalificacionDAO {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public List<Calificacion> getAllCalificaciones() {
        return (List<Calificacion>) calificacionRepository.findAll();
    }

    @Override
    public Optional<Calificacion> getCalificacionById(Long id) {
        return calificacionRepository.findById(id);
    }

    @Override
    public Calificacion updateCalificacion(Long id, Calificacion calificacion) {
        if (calificacionRepository.existsById(id)) {
            calificacion.setId(id);
            return calificacionRepository.save(calificacion);
        } else {
            return null;
        }
    }

    @Override
    public void createCalificacion(Calificacion calificacion) {
        calificacionRepository.save(calificacion);
    }

    @Override
    public void deleteCalificacion(Long id) {
        calificacionRepository.deleteById(id);
    }

    @Override
    public Double findAverageByPublicacionId(Long publicacionId) {
        return calificacionRepository.findAverageByPublicacionId(publicacionId);
    }

}
