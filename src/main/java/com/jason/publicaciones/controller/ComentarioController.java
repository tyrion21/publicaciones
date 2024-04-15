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

import com.jason.publicaciones.dto.ComentarioDto;
import com.jason.publicaciones.model.Comentario;
import com.jason.publicaciones.services.ComentarioService;



@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    

    @Autowired
    private ComentarioService comentarioService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getComentarioById(@PathVariable Long id) {
        Optional<Comentario> comentarioOpcional = comentarioService.getComentarioById(id);

        if (comentarioOpcional.isPresent()) {
            Comentario comentario = comentarioOpcional.get();

            ComentarioDto comentarioDto = ComentarioDto.builder()
                    .id(comentario.getId())
                    .texto(comentario.getTexto())
                    .publicacionId(comentario.getPublicacion())
                    .build();

            return ResponseEntity.ok(comentarioDto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllComentarios() {
        List<ComentarioDto> comentarioList = comentarioService.getAllComentarios()
                .stream()
                .map(comentario -> ComentarioDto.builder()
                        .id(comentario.getId())
                        .texto(comentario.getTexto())
                        .publicacionId(comentario.getPublicacion())
                        .build())
                .toList();
        return ResponseEntity.ok(comentarioList);
    }

    @PostMapping
    public ResponseEntity<?> createComentario(@RequestBody ComentarioDto comentarioDto) {
        
        if(comentarioDto.getTexto().isBlank() || comentarioDto.getPublicacionId() == null){
            return ResponseEntity.badRequest().build();
        }

        Comentario comentario = Comentario.builder()
                .texto(comentarioDto.getTexto())
                .publicacion(comentarioDto.getPublicacionId())
                .build();

        comentarioService.createComentario(comentario);

        return ResponseEntity.ok().body("Comentario creado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComentario(@PathVariable Long id, @RequestBody ComentarioDto comentarioDto) {
        Optional<Comentario> comentarioOpcional = comentarioService.getComentarioById(id);

        if (comentarioOpcional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Comentario comentario = comentarioOpcional.get();

        comentario.setTexto(comentarioDto.getTexto());

        comentarioService.createComentario(comentario);

        return ResponseEntity.ok().body("Comentario actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.ok().body("Comentario eliminado correctamente");
    }


}
