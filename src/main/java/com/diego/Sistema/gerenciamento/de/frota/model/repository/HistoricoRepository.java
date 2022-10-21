package com.diego.Sistema.gerenciamento.de.frota.model.repository;

import com.diego.Sistema.gerenciamento.de.frota.model.entity.HistoricoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel, UUID> {

    Optional<HistoricoModel> findByCodigoEmprestimo(String codigoEmprestimo);
}
