package com.diego.Sistema.gerenciamento.de.frota.util;

import com.diego.Sistema.gerenciamento.de.frota.model.dtos.FuncionarioDto;
import com.diego.Sistema.gerenciamento.de.frota.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificaDadosFuncionarios {

    final FuncionarioRepository funcionarioRepository;
    public VerificaDadosFuncionarios(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public boolean verificaDuplicidadeDados(FuncionarioDto funcionarioDto){

        if(funcionarioRepository.existsByCpf(funcionarioDto.getCpf()) || funcionarioRepository.existsByPis(funcionarioDto.getPis())
            || funcionarioRepository.existsByCnh(funcionarioDto.getCnh())
                || funcionarioRepository.existsByEmail(funcionarioDto.getEmail())){
            return true;
        }
        return false;
    }


}
