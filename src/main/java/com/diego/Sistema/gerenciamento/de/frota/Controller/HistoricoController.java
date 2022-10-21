package com.diego.Sistema.gerenciamento.de.frota.Controller;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.VeiculoDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.HistoricoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;
import com.diego.Sistema.gerenciamento.de.frota.model.service.HistoricoService;
import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/historico")
public class HistoricoController {

    final HistoricoService historicoService;
    final VeiculoService veiculoService;
    public HistoricoController(HistoricoService historicoService, VeiculoService veiculoService) {
        this.historicoService = historicoService;
        this.veiculoService = veiculoService;
    }


    @PostMapping("/devolver")
    public String devolucao( VeiculoDto veiculoDto){

        VeiculoModel veiculoModel = veiculoService.findByPlaca(veiculoDto.getPlaca()).get();
        veiculoModel.setKmRodados(veiculoDto.getKmRodados());
        veiculoModel.setMotoristaModel(null);
        veiculoModel.setStatusVeiculo(StatusVeiculoEnum.DISPONIVEL);


        HistoricoModel historicoModel = historicoService.findByCodigoEmprestimo(veiculoModel.getNumeracaoEmprestimo()).get();
        historicoModel.setDtDevolucao(new Date());
        historicoService.save(historicoModel);
        veiculoService.save(veiculoModel);
        return "redirect:/user";
    }



}
