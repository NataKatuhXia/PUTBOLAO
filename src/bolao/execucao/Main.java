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
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Main {

    public static void main(String[] args) {

//        Pessoa administrador = new Administrador().createAccount("Cadastro", "Teste", "admin", "123");
//
//        Jogo jogo = new Jogo();
//        ControlBolao bolao = new ControlBolao(jogo);
//
//        bolao.registerObserver(administrador);
//        jogo.setCommand(bolao);
//        jogo.setCommand(bolao);
        Date data = new Date();
        DateFormat formataData = DateFormat.getDateInstance();
        System.out.println("Data atual com formatação: " + formataData.format(data));

    }
}
