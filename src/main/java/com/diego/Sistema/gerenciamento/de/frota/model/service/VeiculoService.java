package com.diego.Sistema.gerenciamento.de.frota.model.service;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public boolean existsByPlaca(String placa){
        return veiculoRepository.existsByPlaca(placa);
    }

    @Transactional
    public void save(VeiculoModel veiculoModel) {
        veiculoRepository.save(veiculoModel);
    }

    @Transactional
    public List<VeiculoModel> findAll(){
        return veiculoRepository.findAll();
    }


}
