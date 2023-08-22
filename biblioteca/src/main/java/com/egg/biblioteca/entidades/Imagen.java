package com.egg.biblioteca.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String mime; //Es el atributo que asigna el formato del tipo archivo de la imagen

    private String nombre;

    @Lob 
    //le informamos a Spring que este dato puede ser grande, pesado, muchos bytes    
    @Basic(fetch = FetchType.LAZY) 
    //El tipo de carga va a ser perezosa, LAZY, el contenido el arreglo de bytes, 
    //se va a cargar solamente cuando lo pidamos, haciendo que las query sean mas livianas
    private byte[] contenido; //Arreglo de byte, que va a ser la forma en la que se va a guardar el contenido de la imagen

    public Imagen() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

}
