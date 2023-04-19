/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.repositorios;

import com.egg.biblioteca.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author UsuarioE
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {     //cambio la palabra class por interface
    //lo que hace toda la linea es crear una interface en el repositorio y lo extiende de JPARepository a la clase Libro de tipo Long

    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo") //voy a seleccionar todas las l y hacer una consulta donde el titulo sea igual al que se pasa por parametros

    public Libro buscarPorTitulo(@Param("titulo") String titulo);
    //En esta Query que cree voy a buscar todos todos los libros que tengan el titulo que nosotros enviamos por parametros

    
    
    
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")

    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);
    // En esta Query voy a buscar todos los libros que se relacionen a un determinado autor
     // Puedo tener mas de un libro con el mismo autor asi que por eso le pongo List<Libro> por si me retorna una lista de todos los libros que tiene ese autor
                
        
            }
            