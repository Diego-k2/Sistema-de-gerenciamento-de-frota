package com.diego.Sistema.gerenciamento.de.frota.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    final VeiculoService veiculoService;
    public VeiculoService(VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }

    @Transactional
    public boolean existsByNumeracaoVeiculo(String numeracaoVeiculo) {
        return existsByNumeracaoVeiculo(numeracaoVeiculo);
    }



}
