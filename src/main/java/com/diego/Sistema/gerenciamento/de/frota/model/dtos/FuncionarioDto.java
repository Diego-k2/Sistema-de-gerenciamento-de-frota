package com.diego.Sistema.gerenciamento.de.frota.model.dtos;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class FuncionarioDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String cnh;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private LocalDateTime dtNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDateTime dtNascimento) {
        this.dtNascimento = dtNascimento;
    }


    public FuncionarioModel toFuncionarioModel(){
        FuncionarioModel funcionarioModel = new FuncionarioModel();

        funcionarioModel.setNome(this.nome);
        funcionarioModel.setSobrenome(this.sobrenome);
        funcionarioModel.setCpf(this.cpf);
        funcionarioModel.setCnh(this.cnh);
        funcionarioModel.setEmail(this.email);
        funcionarioModel.setSenha(this.senha);
        funcionarioModel.setDtNascimento(this.dtNascimento);

        return funcionarioModel;
    }
}
