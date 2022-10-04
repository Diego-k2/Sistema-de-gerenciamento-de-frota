package com.diego.Sistema.gerenciamento.de.frota.model.dtos;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;

import javax.validation.constraints.NotBlank;

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
    private String pis;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

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

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }


    public FuncionarioModel toFuncionarioModel(){
        FuncionarioModel funcionarioModel = new FuncionarioModel();

        funcionarioModel.setNome(this.nome);
        funcionarioModel.setSobrenome(this.sobrenome);
        funcionarioModel.setCpf(this.cpf.replace("[^0-9a-zA-Z]+", ""));
        funcionarioModel.setCnh(this.cnh.replace("[^0-9a-zA-Z]+",""));
        funcionarioModel.setEmail(this.email);
        funcionarioModel.setSenha(this.senha);
        funcionarioModel.setPis(this.pis);

        return funcionarioModel;
    }
}
