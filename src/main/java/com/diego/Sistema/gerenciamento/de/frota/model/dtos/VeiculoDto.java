package com.diego.Sistema.gerenciamento.de.frota.model.dtos;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VeiculoDto {

    @NotBlank
    @Size(max = 150)
    private String modelo;

    @NotBlank
    @Size(max = 4)
    private String ano;

    @NotBlank
    @Size(max = 7)
    private String placa;

    @NotBlank
    private String kmRodados;



    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(String kmRodados) {
        this.kmRodados = kmRodados;
    }

    public VeiculoModel toVeiculoModel(){
        VeiculoModel veiculoModel = new VeiculoModel();
        veiculoModel.setModelo(this.modelo);
        veiculoModel.setAno(this.ano);
        veiculoModel.setPlaca(this.placa);
        veiculoModel.setKmRodados(this.kmRodados);
        veiculoModel.setStatusVeiculo(StatusVeiculoEnum.DISPONIVEL);

        return veiculoModel;
    }



}
