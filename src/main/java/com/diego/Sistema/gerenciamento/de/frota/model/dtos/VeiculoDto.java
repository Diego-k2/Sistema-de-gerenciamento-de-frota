package com.diego.Sistema.gerenciamento.de.frota.model.dtos;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;
import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import com.diego.Sistema.gerenciamento.de.frota.util.NumeracaoVeiculo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VeiculoDto {

    final NumeracaoVeiculo numeracaoVeiculo;
    public VeiculoDto(NumeracaoVeiculo numeracaoVeiculo, FuncionarioService funcionarioService) {
        this.numeracaoVeiculo = numeracaoVeiculo;
    }


    @NotBlank
    @Size(max = 150)
    private String modelo;

    @NotBlank
    @Size(max = 4)
    private String ano;

    @NotBlank
    @Size(max = 6)
    private String placa;

    @NotBlank
    private String kmRodados;

    public VeiculoModel toVeiculoModel(){
        VeiculoModel veiculoModel = new VeiculoModel();
        veiculoModel.setModelo(this.modelo);
        veiculoModel.setAno(this.ano);
        veiculoModel.setPlaca(this.placa);
        veiculoModel.setKmRodados(this.kmRodados);
        veiculoModel.setNumeracaoVeiculo(numeracaoVeiculo.numeraVeiculo());
        veiculoModel.setStatusVeiculo(StatusVeiculoEnum.DISPONIVEL);

        return veiculoModel;
    }



}
