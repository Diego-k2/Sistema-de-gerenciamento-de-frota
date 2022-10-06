package com.diego.Sistema.gerenciamento.de.frota.Controller;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.VeiculoDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import com.diego.Sistema.gerenciamento.de.frota.util.NumeracaoVeiculo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {


    final VeiculoService veiculoService;
    final NumeracaoVeiculo numeracaoVeiculo;
    public VeiculoController(VeiculoService veiculoService, NumeracaoVeiculo numeracaoVeiculo) {
        this.veiculoService = veiculoService;
        this.numeracaoVeiculo = numeracaoVeiculo;
    }


    @GetMapping("/novo")
    public String novoVeiculo(){
        return "forms/formNovoVeiculo";
    }

    @PostMapping("/salvarVeiculo")
    public String salvarVeiculo(@Valid VeiculoDto veiculoDto, BindingResult bindingResult){


        VeiculoModel veiculoModel = veiculoDto.toVeiculoModel();
        veiculoModel.setNumeracaoVeiculo(numeracaoVeiculo.numeraVeiculo());
        veiculoService.save(veiculoModel);

        return "redirect:/";
    }
}
