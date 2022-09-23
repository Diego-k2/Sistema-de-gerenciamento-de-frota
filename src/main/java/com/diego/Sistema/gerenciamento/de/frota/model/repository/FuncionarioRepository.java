package com.diego.Sistema.gerenciamento.de.frota.model.repository;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {
}
