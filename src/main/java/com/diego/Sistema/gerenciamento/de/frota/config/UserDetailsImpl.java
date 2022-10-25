package com.diego.Sistema.gerenciamento.de.frota.config;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.service.FuncionarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsImpl implements UserDetailsService {


    final FuncionarioService funcionarioService;
    public UserDetailsImpl(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FuncionarioModel funcionarioModel = funcionarioService.findByEmail(username).get();
        return new User(funcionarioModel.getUsername(), funcionarioModel.getPassword(), true
        ,true,true,true,funcionarioModel.getAuthorities());
    }
}
