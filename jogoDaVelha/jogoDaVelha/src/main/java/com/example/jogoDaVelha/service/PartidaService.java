package com.example.jogoDaVelha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jogoDaVelha.exception.JogadaInvalidaException;
import com.example.jogoDaVelha.exception.PartidaEncerradaException;
import com.example.jogoDaVelha.exception.PartidaNaoEncontradaException;
import com.example.jogoDaVelha.model.Jogador;
import com.example.jogoDaVelha.model.Partida;
import com.example.jogoDaVelha.model.Simbolo;
import com.example.jogoDaVelha.model.StatusPartida;
import com.example.jogoDaVelha.repository.PartidaRepository;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;

    // Injeção por construtor conforme as boas práticas do Spring
    public PartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    public Partida criarPartida(String nomeX, String nomeO) {
        if (nomeX == null || nomeX.isBlank() || nomeO == null || nomeO.isBlank()) {
            throw new IllegalArgumentException("Os nomes dos jogadores não podem ser vazios.");
        }
        Partida partida = new Partida();
        partida.setJogadorX(new Jogador(nomeX, Simbolo.X));
        partida.setJogadorO(new Jogador(nomeO, Simbolo.O));
        return partidaRepository.save(partida);
    }

    public List<Partida> listarTodas() {
        return partidaRepository.findAll();
    }

    public Partida buscarPorId(Long id) {
        return partidaRepository.findById(id)
                .orElseThrow(() -> new PartidaNaoEncontradaException("Partida com ID " + id + " não encontrada."));
    }

    public void excluirPartida(Long id) {
        Partida partida = buscarPorId(id);
        partidaRepository.delete(partida);
    }

    public Partida realizarJogada(Long id, Simbolo simbolo, int linha, int coluna) {
        Partida partida = buscarPorId(id);

        // 1. Validar se a partida está encerrada
        if (partida.getStatus() != StatusPartida.EM_ANDAMENTO) {
            throw new PartidaEncerradaException("Tentativa de jogar em uma partida já encerrada.");
        }

        // 2. Validar o turno do jogador
        if (partida.getTurnoAtual() != simbolo) {
            throw new JogadaInvalidaException("Não é o turno do jogador " + simbolo);
        }

        // 3. Validar limites do tabuleiro
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            throw new JogadaInvalidaException("Posição fora dos limites do tabuleiro (deve ser entre 0 e 2).");
        }

        // 4. Validar se a posição está livre
        if (!partida.isPosicaoLivre(linha, coluna)) {
            throw new JogadaInvalidaException("A posição informada já está ocupada.");
        }

        // Executar a jogada
        partida.marcarPosicao(linha, coluna, simbolo);

        // Verificar fim de jogo
        if (checarVitoria(partida.getTabuleiro(), simbolo)) {
            partida.setStatus(StatusPartida.VITORIA);
            partida.setVencedor(simbolo == Simbolo.X ? partida.getJogadorX() : partida.getJogadorO());
        } else if (checarEmpate(partida.getTabuleiro())) {
            partida.setStatus(StatusPartida.EMPATE);
        } else {
            partida.alternarTurno();
        }

        return partidaRepository.save(partida);
    }

    private boolean checarVitoria(String t, Simbolo s) {
        char c = s.name().charAt(0);
        // Linhas
        if (t.charAt(0) == c && t.charAt(1) == c && t.charAt(2) == c) return true;
        if (t.charAt(3) == c && t.charAt(4) == c && t.charAt(5) == c) return true;
        if (t.charAt(6) == c && t.charAt(7) == c && t.charAt(8) == c) return true;
        // Colunas
        if (t.charAt(0) == c && t.charAt(3) == c && t.charAt(6) == c) return true;
        if (t.charAt(1) == c && t.charAt(4) == c && t.charAt(7) == c) return true;
        if (t.charAt(2) == c && t.charAt(5) == c && t.charAt(8) == c) return true;
        // Diagonais
        if (t.charAt(0) == c && t.charAt(4) == c && t.charAt(8) == c) return true;
        return t.charAt(2) == c && t.charAt(4) == c && t.charAt(6) == c;
    }

    private boolean checarEmpate(String t) {
        return !t.contains("-");
    }
}
