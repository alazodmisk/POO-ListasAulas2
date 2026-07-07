package com.example.jogoDaVelha.controlller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jogoDaVelha.model.Partida;
import com.example.jogoDaVelha.model.Simbolo;
import com.example.jogoDaVelha.service.PartidaService;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<Partida> criar(
            @RequestParam String nomeJogadorX,
            @RequestParam String nomeJogadorO) {
        Partida novaPartida = partidaService.criarPartida(nomeJogadorX, nomeJogadorO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPartida);
    }

    @GetMapping
    public ResponseEntity<List<Partida>> listar() {
        return ResponseEntity.ok(partidaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.buscarPorId(id));
    }

    @PostMapping("/{id}/jogadas")
    public ResponseEntity<Partida> jogar(
            @PathVariable Long id,
            @RequestParam Simbolo simbolo,
            @RequestParam int linha,
            @RequestParam int coluna) {
        Partida partidaAtualizada = partidaService.realizarJogada(id, simbolo, linha, coluna);
        return ResponseEntity.ok(partidaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        partidaService.excluirPartida(id);
        return ResponseEntity.noContent().build();
    }
}
