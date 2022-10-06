package com.diego.Sistema.gerenciamento.de.frota.model.service;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    final VeiculoRepository veiculoRepository;
    public VeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    @Transactional
    public boolean existsByNumeracaoVeiculo(String numeracaoVeiculo) {
        return veiculoRepository.existsByNumeracaoVeiculo(numeracaoVeiculo);
    }

    @Transactional
    public void save(VeiculoModel veiculoModel) {
        veiculoRepository.save(veiculoModel);
    }



}
