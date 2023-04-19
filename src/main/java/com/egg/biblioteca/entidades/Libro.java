/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author UsuarioE
 */

@Entity             // pongo @Entity por que quiero que la clase Libros es una entidad que quiereo que persista de manera permanente sus datos en una abse de datos SQL o en un repositorio 

public class Libro {
    @Id
        
  private Long ISBN;
  private String titulo;
  private Integer ejempleares;
  @Temporal(TemporalType.DATE)
    
  private Date alta;
  
  @ManyToOne
  private Autor autor;
  
  @ManyToOne        //muchos libros tienen 1 sola editorial
  private Editorial editorial;

    public Libro() {
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getEjempleares() {
        return ejempleares;
    }

    public void setEjempleares(Integer ejempleares) {
        this.ejempleares = ejempleares;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
   
    
    
    
}
