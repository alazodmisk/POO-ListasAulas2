package br.com.jogovelha.controller;
import br.com.jogovelha.model.Jogador;
import br.com.jogovelha.model.Simbolo;
import br.com.jogovelha.model.StatusPartida;
import br.com.jogovelha.model.Tabuleiro;
import br.com.jogovelha.view.JogoDaVelhaView;

public class JogoDaVelhaController {

    Tabuleiro tabuleiro = new Tabuleiro();
    JogoDaVelhaView view;
    Jogador jogador1;
    Jogador jogador2;
    Jogador jogadorVencedor;
    StatusPartida status;

    public Jogador cadastrarJogador(Simbolo simbolo){
        String nome = view.cadastrarJogador(simbolo);
        Jogador jogador = new Jogador(nome, simbolo);
        return jogador;
    }

    public void realizarjogada(){
        int linha;
        int coluna;

        if(jogador1.isTurno()){
            linha = view.solicitarLinha(jogador1.getNome());
            coluna = view.solicitarColuna(jogador1.getNome());
        }else{
            linha = view.solicitarLinha(jogador2.getNome());
            coluna = view.solicitarColuna(jogador2.getNome());
        }

        while (!tabuleiro.validaJogada(linha, coluna)){
            view.jogadaInvalida();
            if(jogador1.isTurno()){
                linha = view.solicitarLinha(jogador1.getNome());
                coluna = view.solicitarColuna(jogador1.getNome());
            }else{
                linha = view.solicitarLinha(jogador2.getNome());
                coluna = view.solicitarColuna(jogador2.getNome());
            }
        }

        if(jogador1.isTurno()){
            tabuleiro.trocarSimbolo(linha, coluna, this.jogador1);
        }else{
            tabuleiro.trocarSimbolo(linha, coluna, this.jogador2);
        }

        this.jogador1.trocaTurno();
        this.jogador2.trocaTurno();
    }

    public void jogoDaVelha(){
        this.setJogador1(this.cadastrarJogador(Simbolo.X));
        this.setJogador2(this.cadastrarJogador(Simbolo.O));

        jogador1.trocaTurno();

        this.setStatus(StatusPartida.EM_ANDAMENTO);

        while (this.getStatus() == StatusPartida.EM_ANDAMENTO){

            view.imprimeTabuleiro(tabuleiro.getTabuleiro());
            this.realizarjogada();

            Simbolo simboloVencedor = tabuleiro.verificaVencedor();

            if(simboloVencedor == jogador1.getSimbolo()){
                this.setJogadorVencedor(this.jogador1);
                this.setStatus(StatusPartida.VITORIA);
                view.imprimeTabuleiro(tabuleiro.getTabuleiro());
            }

            if (simboloVencedor == jogador2.getSimbolo()){
                this.setJogadorVencedor(this.jogador2);
                this.setStatus(StatusPartida.VITORIA);
                view.imprimeTabuleiro(tabuleiro.getTabuleiro());
            }

            if (tabuleiro.verificaEmpate()){
                this.setStatus(StatusPartida.EMPATE);
                Jogador velha = new Jogador("VELHA", Simbolo.N);
                this.setJogadorVencedor(velha);;
                view.imprimeTabuleiro(tabuleiro.getTabuleiro());
            }
        }

        view.exibeResultado(jogadorVencedor.getNome());
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    public StatusPartida getStatus() {
        return status;
    }

    public void setStatus(StatusPartida status) {
        this.status = status;
    }

    public Jogador getJogadorVencedor() {
        return jogadorVencedor;
    }

    public void setJogadorVencedor(Jogador jogadorVencedor) {
        this.jogadorVencedor = jogadorVencedor;
    }

    public JogoDaVelhaView getview() {
        return view;
    }

    public void setView(JogoDaVelhaView view) {
        this.view = view;
    }
}
