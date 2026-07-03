package com.guiviana.jogoDaVelha.model;

import jakarta.persistence.Entity;

@Entity
public class Jogador {
    private long id;
    private String nome;
    private char simbolo;

    public Jogador(long id, String nome, char simbolo) {
        this.id = id;
        this.nome = nome;
        this.simbolo = simbolo;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public char getSimbolo() {
        return simbolo;
    }
}
