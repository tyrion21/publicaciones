package com.jason.publicaciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jason.publicaciones.dto.CalificacionDto;
import com.jason.publicaciones.model.Calificacion;
import com.jason.publicaciones.services.CalificacionService;



@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {
    

    @Autowired
    private CalificacionService calificacionService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getCalificacionById(@PathVariable Long id) {
        Optional<Calificacion> calificacionOpcional = calificacionService.getCalificacionById(id);

        if (calificacionOpcional.isPresent()) {
            Calificacion calificacion = calificacionOpcional.get();

            CalificacionDto calificacionDto = CalificacionDto.builder()
                    .id(calificacion.getId())
                    .valor(calificacion.getValor())
                    .publicacionId(calificacion.getPublicacion())
                    .build();

            return ResponseEntity.ok(calificacionDto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCalificaciones() {
        List<CalificacionDto> calificacionList = calificacionService.getAllCalificaciones()
                .stream()
                .map(calificacion -> CalificacionDto.builder()
                        .id(calificacion.getId())
                        .valor(calificacion.getValor())
                        .publicacionId(calificacion.getPublicacion())
                        .build())
                .toList();
        return ResponseEntity.ok(calificacionList);
    }

    @PostMapping
    public ResponseEntity<?> createCalificacion(@RequestBody CalificacionDto calificacionDto) {
        
        if(calificacionDto.getValor() == 0 || calificacionDto.getPublicacionId() == null){
            return ResponseEntity.badRequest().build();
        }

        Calificacion calificacion = Calificacion.builder()
                .valor(calificacionDto.getValor())
                .publicacion(calificacionDto.getPublicacionId())
                .build();

        calificacionService.createCalificacion(calificacion);

        return ResponseEntity.ok().body("Calificacion creado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCalificacion(@PathVariable Long id, @RequestBody CalificacionDto calificacionDto) {
        Optional<Calificacion> calificacionOpcional = calificacionService.getCalificacionById(id);

        if (calificacionOpcional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Calificacion calificacion = calificacionOpcional.get();

        calificacion.setValor(calificacionDto.getValor());

        calificacionService.createCalificacion(calificacion);

        return ResponseEntity.ok().body("Calificacion actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCalificacion(@PathVariable Long id) {
        calificacionService.deleteCalificacion(id);
        return ResponseEntity.ok().body("Calificacion eliminado correctamente");
    }

    @GetMapping("/promedios/{publicacionId}")
    public ResponseEntity<String> getPromedioPublicaciones(@PathVariable Long publicacionId) {
        Double average = calificacionService.findAverageByPublicacionId(publicacionId);
        String message = "El promedio de calificaciones de la publicación " + publicacionId + " es " + average;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
