package com.diego.Sistema.gerenciamento.de.frota.model.service;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.HistoricoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.repository.HistoricoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    final HistoricoRepository historicoRepository;
    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Transactional
    public void save(HistoricoModel historicoModel){
        historicoRepository.save(historicoModel);
    }

    @Transactional
     public Optional<HistoricoModel> findByCodigoEmprestimo(String codigoEmprestimo){
        return historicoRepository.findByCodigoEmprestimo(codigoEmprestimo);
    }

    @Transactional
    public List<HistoricoModel> findAll(){
        return historicoRepository.findAll();
    }



}
