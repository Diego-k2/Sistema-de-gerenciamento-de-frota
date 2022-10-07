package com.diego.Sistema.gerenciamento.de.frota.model.service;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.repository.FuncionarioRepository;
import org.hibernate.annotations.Target;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<FuncionarioModel> findAll(){
        return funcionarioRepository.findAll();
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

    @Transactional
    public Optional<FuncionarioModel> findById(String id){
        return funcionarioRepository.findById(UUID.fromString(id));
    }

    @Transactional
    public void deleteById(String id){
        funcionarioRepository.deleteById(UUID.fromString(id));
    }

    @Transactional
    public Optional<FuncionarioModel> findByEmail(String email){
        return funcionarioRepository.findByEmail(email);
    }

}
