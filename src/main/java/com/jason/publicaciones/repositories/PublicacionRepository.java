package com.jason.publicaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jason.publicaciones.model.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long>{

    
} 