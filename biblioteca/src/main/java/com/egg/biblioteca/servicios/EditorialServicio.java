
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre de la Editorial no puede ser nulo o estar vacio");
        }
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
        
    }
    
    public List<Editorial> listarEditoriales() {
        
        List<Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
        
    }
    
    @Transactional
    public void modificarEditorial(String nombre, String id) throws MiException{
        
        validar(nombre, id);
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Editorial editorial = respuesta.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);

        }
    }
    
    @Transactional
    public void eliminarEditorial(String id) throws MiException {
        
        if (id.isEmpty() || id == null) {
            throw new MiException("El Id de la Editorial no puede ser nulo o estar vacio");
        }
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            editorialRepositorio.delete(respuesta.get());
        }
    }
    
    public Editorial getOne(String id) {
        return editorialRepositorio.getOne(id);
    }
    
    private void validar(String nombre, String id) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre de la Editorial no puede ser nulo o estar vacio");
        }
        
        if (id == null) {
            throw new MiException("El idEditorial no puede ser nulo");
        }
             
    }
    
}
