package com.diego.Sistema.gerenciamento.de.frota.Controller;


import com.diego.Sistema.gerenciamento.de.frota.model.dtos.FuncionarioDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import com.diego.Sistema.gerenciamento.de.frota.util.VerificaDadosFuncionarios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    final FuncionarioService funcionarioService;
    final VerificaDadosFuncionarios verificaDadosFuncionarios;
    public FuncionarioController(FuncionarioService funcionarioService, VerificaDadosFuncionarios verificaDadosFuncionarios) {
        this.funcionarioService = funcionarioService;
        this.verificaDadosFuncionarios = verificaDadosFuncionarios;
    }


    @GetMapping("/novo")
    public String formNovoFuncionario() {
        return "forms/formNovoFuncionario";
    }

    @PostMapping("/salvarFuncionario")
    public String salvarFuncuinario(@Valid FuncionarioDto funcionarioDto, BindingResult bindingResult, Model model){



        if(bindingResult.hasErrors() || verificaDadosFuncionarios.verificaDuplicidadeDados(funcionarioDto)){
            model.addAttribute("erro", "Algum campo em branco ou dados duplicados");
            model.addAttribute("temerro", true);
            return "forms/formNovoFuncionario";
        }

        FuncionarioModel funcionarioModel = funcionarioDto.toFuncionarioModel();

            funcionarioService.save(funcionarioModel);

        return "redirect:/admin";
    }

    @GetMapping("/todos")
    public String todosFuncionarios(Model model){

        List<FuncionarioModel> funcionarios = funcionarioService.findAll();

        model.addAttribute("funcionarios", funcionarios);

        return "funcionario/mostrarFuncionario";
    }

    @GetMapping("/{id}")
    public String funcionarioEspecifico(Model model, @PathVariable("id") String id){

        List<FuncionarioModel> funcionario = new ArrayList<>();
        funcionario.add(funcionarioService.findById(id).get());

        model.addAttribute("funcionario", funcionario);

        return "funcionario/funcionarioUnico";
    }
}















