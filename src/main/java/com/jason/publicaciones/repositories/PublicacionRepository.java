package com.jason.publicaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jason.publicaciones.model.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long>{

    
} 