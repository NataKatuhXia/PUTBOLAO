/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.execucao;

import bolao.controler.ControlBolao;
import bolao.model.bean.Administrador;
import bolao.model.bean.Apostador;
import bolao.model.bean.Jogo;
import bolao.model.bean.Pessoa;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Main {

    public static void main(String[] args) {

        Pessoa administrador = new Administrador().createAccount("Admin", "admin", "123");
        Pessoa apostador1 = new Apostador().createAccount("Apostador1", "apostador1", "123");
        Pessoa apostador2 = new Apostador().createAccount("Apostador2", "apostador2", "123");

        Jogo jogo = new Jogo();
        ControlBolao bolao = new ControlBolao(jogo);

        bolao.registerObserver(administrador);
        bolao.registerObserver(apostador1);
        bolao.registerObserver(apostador2);

        jogo.setCommand(bolao);
        jogo.setCommand(bolao);

    }
}
