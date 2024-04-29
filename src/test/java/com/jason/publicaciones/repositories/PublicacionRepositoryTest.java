package com.jason.publicaciones.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jason.publicaciones.model.Publicacion;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicacionRepositoryTest {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Test
    public void testSavePublicacion() {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Titulo de prueba");
        publicacion.setContenido("Contenido de prueba");
        publicacionRepository.save(publicacion);

        assertNotNull(publicacion.getId());
        assertEquals("Titulo de prueba", publicacion.getTitulo());
        assertEquals("Contenido de prueba", publicacion.getContenido());
    }

    @Test
    public void testGetIdPublicacion() {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Titulo de prueba");
        publicacion.setContenido("Contenido de prueba");
        publicacionRepository.save(publicacion);

        Publicacion publicacion2 = publicacionRepository.findById(publicacion.getId()).get();

        assertNotNull(publicacion2);
        assertEquals(publicacion.getId(), publicacion2.getId());
        assertEquals(publicacion.getTitulo(), publicacion2.getTitulo());
        assertEquals(publicacion.getContenido(), publicacion2.getContenido());
    
    }

}
