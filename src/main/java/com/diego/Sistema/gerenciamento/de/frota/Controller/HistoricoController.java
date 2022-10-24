package com.diego.Sistema.gerenciamento.de.frota.Controller;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.VeiculoDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.HistoricoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;
import com.diego.Sistema.gerenciamento.de.frota.model.service.HistoricoService;
import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/historico")
public class HistoricoController {

    final HistoricoService historicoService;
    final VeiculoService veiculoService;
    public HistoricoController(HistoricoService historicoService, VeiculoService veiculoService) {
        this.historicoService = historicoService;
        this.veiculoService = veiculoService;
    }

    @GetMapping("/todos")
    public String todosEmprestimos(Model model){

        model.addAttribute("historico", historicoService.findAll());

        return "emprestimos/historico";
    }


    @PostMapping("/devolver")
    public String devolucao(VeiculoDto veiculoDto, Model model){

        VeiculoModel veiculoModel = veiculoService.findByPlaca(veiculoDto.getPlaca()).get();

        if(Integer.parseInt(veiculoModel.getKmRodados()) > Integer.parseInt(veiculoDto.getKmRodados())){
            model.addAttribute("temerro", true);
            model.addAttribute("erro", "O KM não pode ser menor que o anterior");
            model.addAttribute("veiculo", veiculoModel);
            return "veiculo/meuveiculo";
        }

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
