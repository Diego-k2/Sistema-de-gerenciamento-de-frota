package com.diego.Sistema.gerenciamento.de.frota.model.service;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {


    final FuncionarioRepository funcionarioRepository;
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public void save(FuncionarioModel funcionarioModel){
         funcionarioRepository.save(funcionarioModel);
    }

    @Transactional
    public boolean existsByCpf(String cpf){
        return funcionarioRepository.existsByCpf(cpf);
    }

    @Transactional
    public boolean existsByEmail(String email){
        return funcionarioRepository.existsByEmail(email);
    }

    @Transactional
    public boolean existsByPis(String pis){
        return funcionarioRepository.existsByPis(pis);
    }

    @Transactional
    public boolean existsByCnh(String cnh){
        return funcionarioRepository.existsByCnh(cnh);
    }





}
