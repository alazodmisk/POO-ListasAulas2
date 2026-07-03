package br.com.jogovelha.model;

public class Tabuleiro {

    private Simbolo[][] tabuleiro = {
            {Simbolo.N, Simbolo.N, Simbolo.N},
            {Simbolo.N, Simbolo.N, Simbolo.N},
            {Simbolo.N, Simbolo.N, Simbolo.N}
    };

    public boolean validaJogada(int linha, int coluna){
        if(linha > 2 || linha < 0 || coluna > 2 || coluna < 0){
            return false;
        }
        
        return this.tabuleiro[linha][coluna] == Simbolo.N;
        //verifica se a jogada é valida
    }

    public void trocarSimbolo(int linha, int coluna, Jogador jogador){
        this.tabuleiro[linha][coluna] = jogador.getSimbolo();
    }

    public Simbolo verificaVencedor(){
        if(this.tabuleiro[0][0] == this.tabuleiro[0][1] && this.tabuleiro[0][0] == this.tabuleiro[0][2]){
            return this.tabuleiro[0][0]; // primeira linha
        }else if(this.tabuleiro[1][0] == this.tabuleiro[1][1] && this.tabuleiro[1][0] == this.tabuleiro[1][2]){
            return this.tabuleiro[1][0]; // segunda linha
        }else if (this.tabuleiro[2][0] == this.tabuleiro[2][1] && this.tabuleiro[2][0] == this.tabuleiro[2][2]) {
            return this.tabuleiro[2][0]; // terceira linha
        }else if (this.tabuleiro[0][0] == this.tabuleiro[1][0] && this.tabuleiro[0][0] == this.tabuleiro[2][0]) {
            return this.tabuleiro[0][0]; // primeira coluna
        }else if (this.tabuleiro[0][1] == this.tabuleiro[1][1] && this.tabuleiro[0][1] == this.tabuleiro[2][1]) {
            return this.tabuleiro[0][1]; // segunda coluna
        }else if (this.tabuleiro[0][2] == this.tabuleiro[1][2] && this.tabuleiro[0][2] == this.tabuleiro[2][2]) {
            return this.tabuleiro[0][2]; // terceira coluna
        }else if (this.tabuleiro[0][0] == this.tabuleiro[1][1] && this.tabuleiro[0][0] == this.tabuleiro[2][2]) {
            return this.tabuleiro[0][0]; // diagonal da esquerda coluna
        }else if (this.tabuleiro[0][2] == this.tabuleiro[1][1] && this.tabuleiro[0][2] == this.tabuleiro[2][0]) {
            return this.tabuleiro[0][2]; // diagonal da direita coluna
        }
        return Simbolo.N;
    }

    public boolean verificaEmpate(){

        boolean posicoesValida = false; //se ainda existe alguma posicao valida

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(this.tabuleiro[i][j] == Simbolo.N){
                    posicoesValida = true;
                }
            }
        }
        
        return this.verificaVencedor() == Simbolo.N && !posicoesValida;
    }

    public Simbolo[][] getTabuleiro() {
        return this.tabuleiro;
    }
}
