/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.Excepciones.MiException;
import com.egg.biblioteca.Servicios.AutorServicio;
import com.egg.biblioteca.entidades.Autor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author UsuarioE
 */
@Controller
@RequestMapping("/autor")       //localhost:8080/autor    cuando haga esto va a cargar la pagina y va a ir directamente a  /autor
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")   //localhost:8080/autor/registrar                //el GetMapping lo que hace es renderizar osea mostrar una pagina actual que en este caso la de autor_form.html 
    public String registrar() {
        return "autor_form.html";       //si todo sale bien va a ir a autor_form.html

    }

    @PostMapping("/registro")   //localhost:8080/autor/registro             //si apretamos el boton en guardar va a cambiar  a esa direccion. el @PostMapping es el que va a recibir todos los datos del formulario
    public String registro(@RequestParam String nombre, ModelMap modelo) {

        try {
            autorServicio.crearAutor(nombre);

            modelo.put("exito", "El autor fue registrado exitosamente!");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }

        return "index.html";    // si esta todo bien cargado nos regresa al index.html y si paso algo mal nos manda la autor_form.html 
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutor();
        modelo.addAttribute("autores", autores);
        return "autor_list.html";

    }

    @GetMapping("/modificar/{id}")      //a poner esto le indico que quiero que a travez de una url que en este caso es /modificar quiero que viaje solamente el fragmento id  hacia otro lado
    public String modificar(@PathVariable String id, ModelMap modelo){          // con el @PathVariable le estoy diciendo que va a viajar una viariable tipo string y que es el id, asi cuadno cargo la otra pagina ya me muestre el nombre del autor id
        
        modelo.put("autor", autorServicio.getOne(id));
        
        return "autor_modificar.html";
    }
    
    
    @PostMapping("modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){       try {
        // con el @PostMapping le indico que va a recibir en esa direccion la variable od y el nombre y con el pathVariable le indico aca en el controlador que solamente se reciben esas 2 variables nadamas de toda la pagina
        
        autorServicio.modificarAutor(nombre, id);
        
        return "redirect:../lista";         //si todo va bien  y se modifica correctamente entonces quiero que me redireccione a la pagina autor/lista con el autor ya modificado
                
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());       //si algo sale mal quiero que me muestre un msj con el error
            return "autor_modificar.html";
        }
    }
}
