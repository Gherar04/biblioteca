/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author UsuarioE
 */

    
@Controller
@RequestMapping("/")/* configura la url que va a escuchar a este controlador con la barra se va a activar*/
public class PortalControlador {  //localhost:8080
    
    @GetMapping("/")
    public String index(){
        
        return "index.html";
    }
}
    

