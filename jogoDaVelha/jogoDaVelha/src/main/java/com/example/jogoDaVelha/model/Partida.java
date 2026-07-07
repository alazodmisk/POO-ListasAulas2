package com.example.jogoDaVelha.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Jogador jogadorX;

    @OneToOne(cascade = CascadeType.ALL)
    private Jogador jogadorO;

    private String tabuleiro = "---------"; // Armazenamento interno no banco

    @Enumerated(EnumType.STRING)
    private Simbolo turnoAtual = Simbolo.X;

    @Enumerated(EnumType.STRING)
    private StatusPartida status = StatusPartida.EM_ANDAMENTO;

    @OneToOne(cascade = CascadeType.ALL)
    private Jogador vencedor;

    // Converte a string de 9 caracteres para a matriz 3x3 exigida no JSON
    @JsonProperty("tabuleiro")
    @Transient
    public String[][] getTabuleiroMatriz() {
        String[][] matriz = new String[3][3];
        for (int i = 0; i < 9; i++) {
            char c = tabuleiro.charAt(i);
            matriz[i / 3][i % 3] = (c == '-') ? null : String.valueOf(c);
        }
        return matriz;
    }

    // Métodos utilitários de negócio
    public void marcarPosicao(int linha, int coluna, Simbolo simbolo) {
        int index = linha * 3 + coluna;
        char[] chars = this.tabuleiro.toCharArray();
        chars[index] = simbolo.name().charAt(0);
        this.tabuleiro = new String(chars);
    }

    public boolean isPosicaoLivre(int linha, int coluna) {
        int index = linha * 3 + coluna;
        return this.tabuleiro.charAt(index) == '-';
    }

    public void alternarTurno() {
        this.turnoAtual = (this.turnoAtual == Simbolo.X) ? Simbolo.O : Simbolo.X;
    }

    // Getters e Setters convencionais
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Jogador getJogadorX() { return jogadorX; }
    public void setJogadorX(Jogador jX) { this.jogadorX = jX; }
    public Jogador getJogadorO() { return jogadorO; }
    public void setJogadorO(Jogador jO) { this.jogadorO = jO; }
    public String getTabuleiro() { return tabuleiro; }
    public void setTabuleiro(String tabuleiro) { this.tabuleiro = tabuleiro; }
    public Simbolo getTurnoAtual() { return turnoAtual; }
    public void setTurnoAtual(Simbolo turno) { this.turnoAtual = turno; }
    public StatusPartida getStatus() { return status; }
    public void setStatus(StatusPartida status) { this.status = status; }
    public Jogador getVencedor() { return vencedor; }
    public void setVencedor(Jogador vencedor) { this.vencedor = vencedor; }
}
