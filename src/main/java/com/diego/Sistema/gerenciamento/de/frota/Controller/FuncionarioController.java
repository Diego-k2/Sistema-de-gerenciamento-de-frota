package com.diego.Sistema.gerenciamento.de.frota.Controller;


import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    @GetMapping("/novo")
    public String novoFuncionario() {
        return "forms/formNovoFuncionario";
    }

}
