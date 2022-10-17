package com.diego.Sistema.gerenciamento.de.frota.Controller;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.VeiculoDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import com.diego.Sistema.gerenciamento.de.frota.util.NumeracaoVeiculo;
import com.diego.Sistema.gerenciamento.de.frota.util.VerificaDados;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {


    final VeiculoService veiculoService;
    final NumeracaoVeiculo numeracaoVeiculo;
    final VerificaDados verificaDados;
    public VeiculoController(VeiculoService veiculoService, NumeracaoVeiculo numeracaoVeiculo, VerificaDados verificaDados) {
        this.veiculoService = veiculoService;
        this.numeracaoVeiculo = numeracaoVeiculo;
        this.verificaDados = verificaDados;
    }


    @GetMapping("/novo")
    public String novoVeiculo(){
        return "veiculo/novoveiculo";
    }

    @PostMapping("/salvarVeiculo")
    public String salvarVeiculo(@Valid VeiculoDto veiculoDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors() || verificaDados.verificaDuplicidadeDadosVeiculo(veiculoDto)){
            model.addAttribute("temerro", true);
            model.addAttribute("erro", "Algum campo em branco ou duplicado");
            return "veiculo/novoveiculo";
        }

        VeiculoModel veiculoModel = veiculoDto.toVeiculoModel();
        veiculoModel.setNumeracaoVeiculo(numeracaoVeiculo.numeraVeiculo());
        veiculoService.save(veiculoModel);

        return "redirect:/veiculo/todos";
    }


    @GetMapping("/todos")
    public String todosVeiculos(Model model) {

        List<VeiculoModel> veiculos = veiculoService.findAll();
        model.addAttribute("veiculos", veiculos);

        return "veiculo/todosveiculos";
    }

    @GetMapping("/delete/{id}")
    public String deleteVeiculo(@PathVariable("id") String id){
        veiculoService.deleteVeiculo(id);
        return "redirect:/veiculo/todos";
    }


}
