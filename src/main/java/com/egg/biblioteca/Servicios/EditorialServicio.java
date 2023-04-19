/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.Servicios;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import java.util.ArrayList;
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
public class EditorialServicio {
    
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional 
    public void CrearEditorial(String nombre){
        
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
        
                       
    }
    public List<Editorial> listarEditoriales(){
        
        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
                
    }
    
    public void modificarEditorial(String id, String nombre){
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
            
            
            
            
            
        }
        
        
    }
    
    
}
