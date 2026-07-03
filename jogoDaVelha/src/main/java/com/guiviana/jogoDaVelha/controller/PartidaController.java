package com.guiviana.jogoDaVelha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guiviana.jogoDaVelha.model.Partida;
import com.guiviana.jogoDaVelha.service.PartidaService;

@Controller
@RequestMapping("/Partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;
    private Partida partida;

//    @GetMapping
//    public Partida imprimirTabuleiro(){
//        return partidaService.imprimirTabuleiro();
//    }

    public PartidaService realizarJogada(){
        return partidaService.realizarJogada(partida);
    }
}
