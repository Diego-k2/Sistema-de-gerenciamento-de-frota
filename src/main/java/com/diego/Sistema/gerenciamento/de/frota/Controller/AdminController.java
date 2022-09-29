package com.diego.Sistema.gerenciamento.de.frota.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping
    public String mainPage(){
        return "mainAdm";
    }
}
