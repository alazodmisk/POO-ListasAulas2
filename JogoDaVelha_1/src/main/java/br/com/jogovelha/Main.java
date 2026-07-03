package br.com.jogovelha;

import br.com.jogovelha.controller.JogoDaVelhaController;
import br.com.jogovelha.view.JogoDaVelhaView;

public class Main {
    public static void main(String[] args){

        JogoDaVelhaController controller = new JogoDaVelhaController();
        JogoDaVelhaView view = new JogoDaVelhaView();

        controller.setView(view);
        view.setController(controller);
        
        controller.jogoDaVelha();
    }
    
}
