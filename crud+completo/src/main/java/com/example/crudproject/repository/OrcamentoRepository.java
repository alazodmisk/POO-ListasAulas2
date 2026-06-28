package com.example.crudproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudproject.model.Orcamento;
import com.example.crudproject.model.StatusOrcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
    public List<Orcamento> findByStatus(StatusOrcamento status);
}
