package com.diego.Sistema.gerenciamento.de.frota.Controller;

import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    final FuncionarioService funcionarioService;
    public UserController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @GetMapping("/user")
    public String userMain(Model model){ //TODO IMPLEMENTAR PRINCIPAL
        model.addAttribute("user", funcionarioService.findByEmail("degosantosiva@gmail.com").get());
        return "principaluser";
    }

}
