package com.guiviana.jogoDaVelha.model;

import jakarta.persistence.Entity;

@Entity
public class Partida {
    private long id;
    private Jogador jogador1;
    private Jogador jogador2;
    private char[][] tabuleiro;
    private StatusPartida status;
    private Jogador vencedor;
    private Simbolo simboloAtual;

    public Partida(long id, Jogador jogador1, Jogador jogador2) {
        this.id = id;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = new char[3][3];
        this.status = StatusPartida.EM_ANDAMENTO;
        this.simboloAtual = Simbolo.X;
    }

    public long getId() {
        return id;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public StatusPartida getStatus() {
        return status;
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    public void setSimboloAtual(Simbolo simboloAtual) {
        this.simboloAtual = simboloAtual;
    }

    public Simbolo getSimboloAtual() {
        return simboloAtual;
    }
}
