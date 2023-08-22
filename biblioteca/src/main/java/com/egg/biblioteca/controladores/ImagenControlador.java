package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    private UsuarioServicio usuarioServicio; //traemos la instancia de UsuarioServicio, para poder utilizar sus metodos

    //Este metodo nos retorna una entidad del tipo ResponseEntity, 
    //nos devuelve la imagen (Contenido, el arreglo de byte) que esta vinculada al perfil de un usuario
    //ResponseEntity<byte[]> tiene un arreglo de byte, el contenido de la imagen
    //lo estabamos guardando en un arreglo de byte
    //el metodo recibe el id del Usuario al que esta vinculada la imagen, mediante @PathVariable a traves de la url
    @GetMapping("/perfil/{id}")    
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) { 
        
        Usuario usuario = usuarioServicio.getOne(id);

        //De este usuario traemos la imagen y el contenido, y lo guardamos en el arreglo de byte
        byte[] imagen = usuario.getImagen().getContenido(); 

        //Esta cabezera le dice al navegador, que lo estamos devolviendo es una imagen
        HttpHeaders headers = new HttpHeaders(); 

        headers.setContentType(MediaType.IMAGE_JPEG); //Al headers le seteamos el tipo de contenido, en este caso una imagen

        //ResponseEntity en su constructor tiene 3 parametros: la imagen (Contenido), 
        //la cabezera y el estado http en el que termina el proceso, en este caso codigo 200
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK); 
    }

}
