
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del Autor no puede ser nulo o estar vacio");
        }
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
        
    }
    
    public List<Autor> listarAutores() {
        
        List<Autor> autores = new ArrayList();
        
        autores = autorRepositorio.findAll();
        
        return autores;
        
    }
   
    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException {
        
        validar(nombre, id);
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Autor autor = respuesta.get();
            
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);

        }
    }
    
    @Transactional
    public void eliminarAutor(String id) throws MiException {
        
        if (id.isEmpty() || id == null) {
            throw new MiException("El Id del Autor no puede ser nulo o estar vacio");
        }
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            autorRepositorio.delete(respuesta.get());
        }
    }
    
    public Autor getOne(String id) {
        return autorRepositorio.getOne(id);
    }
    
    
    private void validar(String nombre, String id) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del Autor no puede ser nulo o estar vacio");
        }
        
        if (id == null) {
            throw new MiException("El idAutor no puede ser nulo");
        }
    
    }

}
