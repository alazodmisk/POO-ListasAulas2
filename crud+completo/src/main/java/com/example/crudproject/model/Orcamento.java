package com.example.crudproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descricao;
    private double valor;
    @Enumerated(EnumType.STRING)
    private StatusOrcamento status = StatusOrcamento.PENDENTE;

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public StatusOrcamento getStatus() {
        return StatusOrcamento.PENDENTE;
    }

    public void setStatus(StatusOrcamento status) {
        this.status = status;
    }
}
