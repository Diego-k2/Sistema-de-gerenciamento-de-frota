package com.diego.Sistema.gerenciamento.de.frota.Controller;


import com.diego.Sistema.gerenciamento.de.frota.model.dtos.FuncionarioDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import com.diego.Sistema.gerenciamento.de.frota.util.VerificaDadosFuncionarios;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("deletar/{id}")
    public String deletarFuncionario(Model model, @PathVariable("id")String id) {
            funcionarioService.deleteById(id);
        return "redirect:/funcionario/todos";
    }


    @GetMapping("/alterar/{id}")
    public String alterarFuncionario(@PathVariable("id") String id, Model model){
        FuncionarioModel funcionarioModel = funcionarioService.findById(id).get();
        model.addAttribute("funcionario", funcionarioModel);
        return "forms/editarFuncionario";
    }

    @PutMapping("/alterando") //TODO ARRUMAR METODO PUT
    public String alterandoFuncionario(@Valid FuncionarioDto funcionarioDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors() || verificaDadosFuncionarios.verificaDuplicidadeDados(funcionarioDto)){
            model.addAttribute("erro", "Algum campo em branco ou dados duplicados");
            model.addAttribute("temerro", true);
            return "forms/formNovoFuncionario";
        }

        FuncionarioModel funcionarioModel = funcionarioService.findByEmail(funcionarioDto.getEmail()).get();

        funcionarioModel.setNome(funcionarioDto.getNome());
        funcionarioModel.setSobrenome(funcionarioDto.getSobrenome());
        funcionarioModel.setCpf(funcionarioDto.getCpf());
        funcionarioModel.setCnh(funcionarioDto.getCnh());
        funcionarioModel.setPis(funcionarioDto.getPis());
        funcionarioModel.setEmail(funcionarioDto.getEmail());

        funcionarioService.save(funcionarioModel);

        return "redirect:/funcionario/todos";
    }

}















