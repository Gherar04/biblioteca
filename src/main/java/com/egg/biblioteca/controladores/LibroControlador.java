/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.Excepciones.MiException;
import com.egg.biblioteca.Servicios.AutorServicio;
import com.egg.biblioteca.Servicios.EditorialServicio;
import com.egg.biblioteca.Servicios.LibroService;
import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author UsuarioE
 */
public class LibroControlador {
    
    @Autowired
    private LibroService libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;
      
      @GetMapping("/registrar")//localhost:8080/libro/registrar
      public String registrar(ModelMap modelo){
          List<Autor> autores = autorServicio.listarAutor();        //traigo la lista de todos los autores y los guardo en la lista tipo autores.
          List<Editorial> editoriales= editorialServicio.listarEditoriales();   // lo mismo con editorial
          
          modelo.addAttribute("autores", autores);
          modelo.addAttribute("editoriales", editoriales);
          
          
          return "libro_form.html";
      }
      
      @PostMapping("/registro")
      public String registro(@RequestParam(required=false) Long isbn, @RequestParam String titulo,      //pongo por parametro requerido en false para que en caso de que no ingrese un isbn si viene nulo  ingresa igual al controlador y continua
              @RequestParam(required=false) Integer ejemplares, @RequestParam String idAutor,
              @RequestParam String idEditorial, ModelMap modelo) {        // pongo ModelMap modelo que nos permite mostrar algo que queremos en pantalla.Por ejemplo q si queremos que muestre un cartel en la pagina diciendo que nos olvidamos de ingresar algun dato   
          try {
              libroServicio.crearLibro(isbn, titulo, Integer.BYTES, titulo, idEditorial); //si todo sale bien retornamos al index.html
          
             modelo.put("exito", "El libro fue cargado exitosamente");
          } catch (MiException ex) {
              
              modelo.put("error", ex.getMessage());
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null,ex);
              
          return "Libro_form.html";  //volvemos a cargar el formulario si sale algo mal 
          
           }
          return "index.html";      // si esta todo bien lo que cargamos nos muestra el index.html y si no nos vuelve a recargar el Libro_form.html
          
          
      }
      
      
      @GetMapping("/lista")
      public String listar(ModelMap modelo){
          List<Libro> libros = libroServicio.listarLibros();
          modelo.addAttribute("libros", libros);
          
          return "libro_list";
          
      }
    
}
