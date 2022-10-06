package com.diego.Sistema.gerenciamento.de.frota.model.entity;

import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_VEICULOS")
public class VeiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false, length = 150)
    private String modelo;

    @Column(nullable = false, length = 4)
    private String ano;

    @Column(nullable = false, unique = true, length = 7)
    private String placa;

    @Column(nullable = false)
    private String kmRodados;

    @Column(unique = true)
    private String numeracaoVeiculo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusVeiculoEnum statusVeiculo;

    @OneToOne
    private FuncionarioModel motoristaModel;


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

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

    public StatusVeiculoEnum getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(StatusVeiculoEnum statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public FuncionarioModel getMotoristaModel() {
        return motoristaModel;
    }

    public void setMotoristaModel(FuncionarioModel motoristaModel) {
        this.motoristaModel = motoristaModel;
    }

    public String getNumeracaoVeiculo() {
        return numeracaoVeiculo;
    }

    public void setNumeracaoVeiculo(String numeracaoVeiculo) {
        this.numeracaoVeiculo = numeracaoVeiculo;
    }
}
