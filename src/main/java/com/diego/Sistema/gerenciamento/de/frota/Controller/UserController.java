package com.diego.Sistema.gerenciamento.de.frota.Controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userMain(){
        return "principaluser";
    }

}
