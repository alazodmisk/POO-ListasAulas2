package com.example.crudproject.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descricao;
    private double valor;
    @Enumerated(EnumType.STRING)
    private StatusOrcamento status = StatusOrcamento.PENDENTE;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToMany
    @JoinTable(
    name = "orcamento_produto", // Nome da tabela intermediária que o JPA vai criar
    joinColumns = @JoinColumn(name = "orcamento_id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

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

    public void aprovar() {
        this.status = StatusOrcamento.AUTORIZADO;
    }

    public void rejeitar() {
        this.status = StatusOrcamento.REJEITADO;
    }
    
    public double calcularValorTotal() {
        return produtos.stream()
                        .mapToDouble(Produto::getPreco)
                        .sum();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
