package com.asm.market;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hola-mundo")

public class HolaMundoController {

    @GetMapping("/saludo")
    public String saludar(){
        return "Primer controlador usando Spring!";
    }

}
