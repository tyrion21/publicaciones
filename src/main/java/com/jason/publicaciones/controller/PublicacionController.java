package com.jason.publicaciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jason.publicaciones.dto.PublicacionDto;
import com.jason.publicaciones.model.Publicacion;
import com.jason.publicaciones.services.PublicacionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/publicaciones")

public class PublicacionController {

    private static final Logger logger = LoggerFactory.getLogger(PublicacionController.class);
        
    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublicacionById(@PathVariable Long id) {
        logger.info("Obteniendo publicacion con id {}", id);
        Optional<Publicacion> publicacionOpcional = publicacionService.getPublicacionById(id);

        if(publicacionOpcional.isPresent()){
            Publicacion publicacion = publicacionOpcional.get();

            PublicacionDto publicacionDto = PublicacionDto.builder()
                .id(publicacion.getId())
                .titulo(publicacion.getTitulo())
                .contenido(publicacion.getContenido())
                .comentarioList(publicacion.getComentarioList())
                .calificacionList(publicacion.getCalificacionList())
                .build();
            logger.info("Publicacion encontrada: {}", publicacionDto);
            return ResponseEntity.ok(publicacionDto);
        }else{
            logger.info("Publicacion no encontrada");
        }

        return ResponseEntity.notFound().build();        

    }
    
    @GetMapping
    public ResponseEntity<?> getAllPublicacion() {
        logger.info("Obteniendo publicaciones");
        List<PublicacionDto> publicacionList = publicacionService.getAllPublicacion()
            .stream()
            .map(publicacion -> PublicacionDto.builder()
                .id(publicacion.getId())
                .titulo(publicacion.getTitulo())
                .contenido(publicacion.getContenido())
                .comentarioList(publicacion.getComentarioList())
                .calificacionList(publicacion.getCalificacionList())
                .build())
            .toList();
        return ResponseEntity.ok(publicacionList);
    }

    @PostMapping
    public ResponseEntity<?> createPublicacion(@RequestBody PublicacionDto publicacionDto) {

        logger.info("Creando publicacion: {}", publicacionDto);
        
        if(publicacionDto.getTitulo().isBlank()){
            logger.error("El titulo es requerido");
            return ResponseEntity.badRequest().body("El titulo es requerido");
        }

        publicacionService.createPublicacion(Publicacion.builder()
            .titulo(publicacionDto.getTitulo())
            .contenido(publicacionDto.getContenido())
            .build());

        logger.info("Publicacion creada");

        return ResponseEntity.ok().body("Publicacion creada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublicacion(@PathVariable Long id, @RequestBody PublicacionDto publicacionDto) {
        logger.info("Actualizando publicacion con id {}", id);
        Optional<Publicacion> publicacionOpcional = publicacionService.getPublicacionById(id);

        if(publicacionOpcional.isPresent()){
            Publicacion publicacion = publicacionOpcional.get();

            publicacion.setTitulo(publicacionDto.getTitulo());
            publicacion.setContenido(publicacionDto.getContenido());

            publicacionService.createPublicacion(publicacion);

            logger.info("Publicacion actualizada");

            return ResponseEntity.ok().body("Publicacion actualizada");
        }

        logger.warn("Publicacion no encontrada");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublicacion(@PathVariable Long id) {
        logger.info("Eliminando publicacion con id {}", id);
        if(id != null){
            publicacionService.deletePublicacion(id);
            logger.info("Publicacion eliminada");
            return ResponseEntity.ok().body("Publicacion eliminada");
        }
        logger.error("Id es requerido");
        return ResponseEntity.badRequest().build();
    }
} 
