package com.diego.Sistema.gerenciamento.de.frota.Controller;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.VeiculoDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.HistoricoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;
import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import com.diego.Sistema.gerenciamento.de.frota.model.service.HistoricoService;
import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import com.diego.Sistema.gerenciamento.de.frota.util.NumeracaoVeiculo;
import com.diego.Sistema.gerenciamento.de.frota.util.VerificaDados;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {


    final VeiculoService veiculoService;
    final NumeracaoVeiculo numeracaoVeiculo;
    final VerificaDados verificaDados;
    final FuncionarioService funcionarioService;
    final HistoricoService historicoService;
    public VeiculoController(VeiculoService veiculoService, NumeracaoVeiculo numeracaoVeiculo, VerificaDados verificaDados,
                             FuncionarioService funcionarioService, HistoricoService historicoService) {
        this.veiculoService = veiculoService;
        this.numeracaoVeiculo = numeracaoVeiculo;
        this.verificaDados = verificaDados;
        this.funcionarioService = funcionarioService;
        this.historicoService = historicoService;
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

        List<VeiculoModel> veiculos = veiculoService.findAllByIsAtivo();
        model.addAttribute("veiculos", veiculos);

        return "veiculo/todosveiculos";
    }

    @GetMapping("/delete/{id}")
    public String deleteVeiculo(@PathVariable("id") String id){
        VeiculoModel veiculoModel = veiculoService.findVeiculoById(id).get();
        veiculoModel.setIsAtivo(0);
        veiculoService.save(veiculoModel);
        return "redirect:/veiculo/todos";
    }

    @GetMapping("/editar/{id}")
    public String editarVeiculo(@PathVariable("id")String id, Model model){
        VeiculoModel veiculoModel = veiculoService.findVeiculoById(id).get();
        model.addAttribute("veiculo", veiculoModel);

        return "/veiculo/editarveiculo";
    }

    @PutMapping("/alterar")
    public String editandoVeiculo(VeiculoDto veiculoDto){
        VeiculoModel veiculoModel = veiculoService.findByPlaca(veiculoDto.getPlaca()).get();

        veiculoModel.setModelo(veiculoDto.getModelo());
        veiculoModel.setAno(veiculoDto.getAno());
        veiculoModel.setPlaca(veiculoDto.getPlaca());
        veiculoModel.setKmRodados(veiculoDto.getKmRodados());

        veiculoService.save(veiculoModel);

        return "redirect:/veiculo/todos";
    }


    @GetMapping("/{status}")
    public String veiculosDisponiveis(@PathVariable("status") String status, Model model){

        List<VeiculoModel> veiculoModelList = veiculoService.findAllByStatusVeiculo(status.toUpperCase(Locale.ROOT));

        model.addAttribute("veiculos", veiculoModelList);
        return "veiculo/veiculostatus";
    }

    @GetMapping("/solicitar/{id}")
    public String solicitandoVeiculo(@PathVariable("id") String id, Model model){

       VeiculoModel veiculoModel = veiculoService.findVeiculoById(id).get();

       if(veiculoService.existsByMotoristaModel(funcionarioService.findByEmail("degosantosiva@gmail.com").get())){
           model.addAttribute("temVeiculo", true);
           model.addAttribute("erro", "Motorista já possui veiculo ou possui solicitação em análise");
           List<VeiculoModel> veiculoModelList = veiculoService.findAllByStatusVeiculo("DISPONIVEL");
           model.addAttribute("veiculos", veiculoModelList);
           return "veiculo/veiculostatus";
       }

       veiculoModel.setMotoristaModel(funcionarioService.findByEmail("degosantosiva@gmail.com").get());

       veiculoModel.setStatusVeiculo(StatusVeiculoEnum.AGUARDANDO_LIBERAÇÃO);

       veiculoService.save(veiculoModel);

        return "redirect:/user";
    }

    @GetMapping("/aguardando")
    public String aguardandoLiberacao(Model model){

        List<VeiculoModel> veiculoModelList = veiculoService.findAllByStatusVeiculo(String.valueOf(StatusVeiculoEnum.AGUARDANDO_LIBERAÇÃO));

        model.addAttribute("veiculos", veiculoModelList);

        return "/veiculo/aguardandoliberacao";
    }

    @GetMapping("/aprovacao/{id}")
    public String aprovado(@RequestParam("aprovado")String aprovado, @PathVariable("id")String id){

        VeiculoModel veiculoModel = veiculoService.findVeiculoById(id).get();
        HistoricoModel historicoModel = new HistoricoModel();
        String codigoEmprestimo = numeracaoVeiculo.geraCodigoEmprestimo();
        if(Boolean.parseBoolean(aprovado)) {
            veiculoModel.setStatusVeiculo(StatusVeiculoEnum.EM_USO);
            veiculoModel.setNumeracaoEmprestimo(codigoEmprestimo);
            historicoModel.setDtEmprestimo(new Date());
            historicoModel.setVeiculoModel(veiculoModel);
            historicoModel.setCodigoEmprestimo(codigoEmprestimo);
            historicoService.save(historicoModel);
        } else if(!Boolean.parseBoolean(aprovado)){
            veiculoModel.setMotoristaModel(null);
            veiculoModel.setStatusVeiculo(StatusVeiculoEnum.DISPONIVEL);
        }

        veiculoService.save(veiculoModel);

        return "redirect:/veiculo/aguardando";
    }

    @GetMapping("/meu/atual")
    public String veiculoEmprestado(Model model){ //USAR O PRINCIPAL PRA FAZER ISSO

       FuncionarioModel funcionarioModel = funcionarioService.findByEmail("degosantosiva@gmail.com").get();

       VeiculoModel veiculoModel = veiculoService.findByMotoristaModel(funcionarioModel);

       if(veiculoModel == null || veiculoModel.getStatusVeiculo() != StatusVeiculoEnum.EM_USO){
           model.addAttribute("temerro", true);
           model.addAttribute("erro", "Usuario não possui veiculo em seu nome");
           return "veiculo/meuveiculo";
       }

       model.addAttribute("veiculo", veiculoModel);

       return "veiculo/meuveiculo";
    }




}
