package com.diego.Sistema.gerenciamento.de.frota.Controller;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.VeiculoDto;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;
import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import com.diego.Sistema.gerenciamento.de.frota.util.NumeracaoVeiculo;
import com.diego.Sistema.gerenciamento.de.frota.util.VerificaDados;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {


    final VeiculoService veiculoService;
    final NumeracaoVeiculo numeracaoVeiculo;
    final VerificaDados verificaDados;
    final FuncionarioService funcionarioService;
    public VeiculoController(VeiculoService veiculoService, NumeracaoVeiculo numeracaoVeiculo, VerificaDados verificaDados, FuncionarioService funcionarioService) {
        this.veiculoService = veiculoService;
        this.numeracaoVeiculo = numeracaoVeiculo;
        this.verificaDados = verificaDados;
        this.funcionarioService = funcionarioService;
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
    public String solicitandoVeiculo(@PathVariable("id") String id){

       VeiculoModel veiculoModel = veiculoService.findVeiculoById(id).get();

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

        //TODO METODO PARA NAO LIBERAR VEICULO CASO FUNCIONARIO JA TENHA UM EM USO

        if(Boolean.parseBoolean(aprovado)) {
            veiculoModel.setStatusVeiculo(StatusVeiculoEnum.EM_USO);
        } else if(!Boolean.parseBoolean(aprovado)){
            veiculoModel.setMotoristaModel(null);
            veiculoModel.setStatusVeiculo(StatusVeiculoEnum.DISPONIVEL);
        }

        veiculoService.save(veiculoModel);

        return "redirect:/veiculo/aguardando";
    }


}
