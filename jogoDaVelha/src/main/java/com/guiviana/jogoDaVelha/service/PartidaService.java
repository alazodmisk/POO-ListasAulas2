package com.guiviana.jogoDaVelha.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.guiviana.jogoDaVelha.model.Jogador;
import com.guiviana.jogoDaVelha.model.Partida;
import com.guiviana.jogoDaVelha.model.Simbolo;

@Service
public class PartidaService {
    Scanner scanner = new Scanner(System.in);
    
    public void criarPartida() {
        System.out.println("Digite o nome do Jogador 1 (X):");
        String nomeJogador1 = scanner.nextLine();
        System.out.println("Digite o nome do Jogador 2 (O):");
        String nomeJogador2 = scanner.nextLine();
        
        long idPartida = System.currentTimeMillis();
        long idJogador1 = System.currentTimeMillis() + 1;
        long idJogador2 = System.currentTimeMillis() + 2;
        Partida partida = new Partida(idPartida, new Jogador(idJogador1, nomeJogador1, 'X'), new Jogador(idJogador2, nomeJogador2, 'O'));
    }

    public void realizarJogada(Partida partida) {
        System.out.println("Jogador de Simbolo " + partida.getSimboloAtual() + ", digite a linha (0-2):");
        int linha = scanner.nextInt();
        System.out.println("Digite a coluna (0-2):");
        int coluna = scanner.nextInt();
        
        if (partida.getTabuleiro()[linha][coluna] == '-') {
            
            if(partida.getSimboloAtual() == Simbolo.X) {
                partida.getTabuleiro()[linha][coluna] = 'X';
            } else {
                partida.getTabuleiro()[linha][coluna] = 'O';
            }
            
            // Verificar vitória ou empate aqui (lógica não implementada)

            partida.setSimboloAtual(partida.getSimboloAtual() == Simbolo.X ? Simbolo.O : Simbolo.X);
        } else {
            System.out.println("Posição já ocupada. Tente novamente.");
        }
    }
}
