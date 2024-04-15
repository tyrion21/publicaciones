package com.jason.publicaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jason.publicaciones.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{


} 
