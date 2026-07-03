package br.com.jogovelha.view;
import java.util.Scanner;

import br.com.jogovelha.controller.JogoDaVelhaController;
import br.com.jogovelha.model.Simbolo;


public class JogoDaVelhaView {

    Scanner scanner = new Scanner(System.in);
    JogoDaVelhaController controller;

    public String cadastrarJogador(Simbolo simbolo){
        String nome;
        do { 
            System.out.println("Digite o nome do Jogador que tera o simbolo " + simbolo + ": ");
            nome = scanner.nextLine();
        } while (nome.isEmpty());
        
        return nome;
    }

    public int solicitarLinha(String nome){
        int linha;
        
        System.out.println("Ola " + nome);
        System.out.println("Digite a LINHA que deseja jogar: ");
        linha = scanner.nextInt();

        linha = linha - 1;

        return linha;
    }

    public int solicitarColuna(String nome){
        int coluna;

        System.out.println("Ola de novo " + nome);
        System.out.println("Digite a COLUNA que deseja jogar: ");
        coluna = scanner.nextInt();

        coluna = coluna - 1;

        return coluna;
    }

    public void imprimeTabuleiro(Simbolo[][] tabuleiro){

        System.out.println("\n   1   2   3");

        for(int i = 0; i < tabuleiro.length; i++){

            System.out.print((i + 1) + " ");

            for(int j = 0; j < tabuleiro[i].length; j++){
                if (tabuleiro[i][j] != Simbolo.N) {
                    System.out.print(" " + tabuleiro[i][j] + " ");
                }else{
                    System.out.print("   ");
                }

                if(j < tabuleiro[i].length - 1){
                    System.out.print("|");
                }
            }

            System.out.println();

            if(i < tabuleiro.length - 1){
                System.out.println("  ---|---|---");
            }
        }
    }

    public void jogadaInvalida(){
        System.out.println("Jogada Inválida, digite novamente");
    }

    public void exibeResultado(String nomeVencedor){
        if(nomeVencedor == "VELHA"){
            System.out.println("Empate entre os participantes!!");
        }else{
            System.out.println("Parabens " + nomeVencedor + " você venceu o jogo!");
        }
    }

    public JogoDaVelhaController getController() {
        return controller;
    }

    public void setController(JogoDaVelhaController controller) {
        this.controller = controller;
    }
}
