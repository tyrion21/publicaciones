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


@RestController
@RequestMapping("/publicaciones")

public class PublicacionController {
        
    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublicacionById(@PathVariable Long id) {
        Optional<Publicacion> publicacionOpcional = publicacionService.getPublicacionById(id);

        if(publicacionOpcional.isPresent()){
            Publicacion publicacion = publicacionOpcional.get();

            PublicacionDto publicacionDto = PublicacionDto.builder()
                .id(publicacion.getId())
                .titulo(publicacion.getTitulo())
                .contenido(publicacion.getContenido())
                .comentarioList(publicacion.getComentarioList())
                .build();

            return ResponseEntity.ok(publicacionDto);
        }

        return ResponseEntity.notFound().build();

    }
    
    @GetMapping
    public ResponseEntity<?> getAllPublicacion() {
        
        List<PublicacionDto> publicacionList = publicacionService.getAllPublicacion()
            .stream()
            .map(publicacion -> PublicacionDto.builder()
                .id(publicacion.getId())
                .titulo(publicacion.getTitulo())
                .contenido(publicacion.getContenido())
                .comentarioList(publicacion.getComentarioList())
                .build())
            .toList();
        return ResponseEntity.ok(publicacionList);
    }

    @PostMapping
    public ResponseEntity<?> createComentario(@RequestBody PublicacionDto publicacionDto) {
        
        if(publicacionDto.getTitulo().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        publicacionService.createPublicacion(Publicacion.builder()
            .titulo(publicacionDto.getTitulo())
            .contenido(publicacionDto.getContenido())
            .build());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublicacion(@PathVariable Long id, @RequestBody PublicacionDto publicacionDto) {
        
        Optional<Publicacion> publicacionOpcional = publicacionService.getPublicacionById(id);

        if(publicacionOpcional.isPresent()){
            Publicacion publicacion = publicacionOpcional.get();

            publicacion.setTitulo(publicacionDto.getTitulo());
            publicacion.setContenido(publicacionDto.getContenido());

            publicacionService.createPublicacion(publicacion);

            return ResponseEntity.ok().body("Publicacion actualizada");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublicacion(@PathVariable Long id) {
        
        if(id != null){
            publicacionService.deletePublicacion(id);
            return ResponseEntity.ok().body("Publicacion eliminada");
        }
        return ResponseEntity.badRequest().build();
    }
} 
