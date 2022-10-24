package com.diego.Sistema.gerenciamento.de.frota.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_EMPRESTIMOS")
public class HistoricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private Date dtEmprestimo;

    private Date dtDevolucao;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private VeiculoModel veiculoModel;

    private String codigoEmprestimo;

    private String motoristaNome;

    public VeiculoModel getVeiculoModel() {
        return veiculoModel;
    }

    public void setVeiculoModel(VeiculoModel veiculoModel) {
        this.veiculoModel = veiculoModel;
    }

    public Date getDtEmprestimo() {
        return dtEmprestimo;
    }

    public void setDtEmprestimo(Date dtEmprestimo) {
        this.dtEmprestimo = dtEmprestimo;
    }

    public Date getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(Date dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCodigoEmprestimo() {
        return codigoEmprestimo;
    }

    public void setCodigoEmprestimo(String codigoEmprestimo) {
        this.codigoEmprestimo = codigoEmprestimo;
    }

    public String getMotoristaNome() {
        return motoristaNome;
    }

    public void setMotoristaNome(String motoristaNome) {
        this.motoristaNome = motoristaNome;
    }
}
