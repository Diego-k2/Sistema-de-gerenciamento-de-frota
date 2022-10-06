package com.diego.Sistema.gerenciamento.de.frota.util;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.FuncionarioDto;
import com.diego.Sistema.gerenciamento.de.frota.model.dtos.VeiculoDto;
import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import org.springframework.stereotype.Service;


@Service
public class VerificaDados {

    final FuncionarioService funcionarioService;
    final VeiculoService veiculoService;
    public VerificaDados(FuncionarioService funcionarioService, VeiculoService veiculoService) {
        this.funcionarioService = funcionarioService;
        this.veiculoService = veiculoService;
    }


    public boolean verificaDuplicidadeDadosFuncionario(FuncionarioDto funcionarioDto){

        if(funcionarioService.existsByCpf(funcionarioDto.getCpf()) || funcionarioService.existsByPis(funcionarioDto.getPis())
            || funcionarioService.existsByCnh(funcionarioDto.getCnh())
                || funcionarioService.existsByEmail(funcionarioDto.getEmail())){
            return true;
        }
        return false;
    }

    public boolean verificaDuplicidadeDadosVeiculo(VeiculoDto veiculoDto) {

        if(veiculoService.existsByPlaca(veiculoDto.getPlaca())) {
            return true;
        }
        return false;
    }




}
