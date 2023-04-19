/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.Servicios;

import com.egg.biblioteca.Excepciones.MiException;
import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author UsuarioE
 */
@Service
public class LibroService {

    @Autowired  // Le indico al servidor de aplicaciones que la variable va a ser inicializada por el.
    private LibroRepositorio libroRepositorio;       // con esto genero una instancia de libroRepositorio. Una vez creado el Libro tenemos que llamar al repositorio para persistir el objeto
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional      //realiza una transaccion y si el emtodo se realiza sin realizar excepciones se hace un commit y e caso de que ahga una excepcion y no es atrapado vuelve para atras(roolback) y no se hace nada en la base de datos.

    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);    //va validando lo que ingresa en usuario antes que se guarde en la base de datos
        Autor autor = autorRepositorio.findById(idAutor).get();  //busca el autor por id en el repositorio y el get lo devuelve a ese autorque encontro por id y lo guarda en autor

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();

        libro.setISBN(isbn);
        libro.setTitulo(titulo);
        libro.setEjempleares(ejemplares);
        libro.setAlta(new Date());  // el Date lo pongo para  que se instancie con la fecha en el momento en que se cree el objeto  se crea

        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();       //creo un arrayList

        libros = libroRepositorio.findAll();  //findAll encuentra todas las tablas de los libros que se encuentran en librosRepositorio y los rellena en libros

        return libros;

    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {
       validar(isbn, titulo, ejemplares, idAutor, idEditorial);         
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);        //el Optional en un objeto que puede contener o no un valor nulo, si tiene un valor devuelve true.
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get();

        }

        if (respuestaEditorial.isPresent()) {

            editorial = respuestaEditorial.get();
        }
        if (respuesta.isPresent()){
        Libro libro = respuesta.get();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setEjempleares(ejemplares);
        libroRepositorio.save(libro);

    }
}




private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        if (isbn == null) {
            throw new MiException("El isbn no puede ser nulo");
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulono puede ser nulo o estar vacio");
        }

        if (ejemplares == null) {
            throw new MiException("Los ejemplares no puede ser nulo");
        }
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El idAutor no puede ser nulo o estar vacio");
        }

        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("El idEditorial no puede ser nulo o estar vacio");
        }
}

}
