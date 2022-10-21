package com.diego.Sistema.gerenciamento.de.frota.model.repository;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import com.diego.Sistema.gerenciamento.de.frota.model.entity.VeiculoModel;
import com.diego.Sistema.gerenciamento.de.frota.model.enums.StatusVeiculoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoModel, UUID> {


    boolean existsByNumeracaoVeiculo(String numeracao);

    boolean existsByPlaca(String placa);

    boolean existsByMotoristaModel(FuncionarioModel funcionarioModel);

    boolean existsByNumeracaoEmprestimo(String numeracaoEmprestimo);

    Optional<VeiculoModel> findByPlacaAndIsAtivo(String numeracaoVeiculo, int isAtivo);

    List<VeiculoModel> findAllByStatusVeiculoAndAndIsAtivo(StatusVeiculoEnum status, int isAtivo);

    VeiculoModel findByMotoristaModelAndIsAtivo(FuncionarioModel funcionarioModel, int isAtivo);

    List<VeiculoModel> findAllByIsAtivo(int ativo);

}
