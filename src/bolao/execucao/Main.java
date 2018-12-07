/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.execucao;

import bolao.controler.ControlTime;
import static bolao.controler.ControlTime.times;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
//        Date data = new Date();
//        DateFormat formataData = DateFormat.getDateInstance();
//        System.out.println("Data atual com formatação: " + formataData.format(data));
//
//            boolean teste = true;
//            
//            System.out.println(String.valueOf(teste));
//        String frase = "4x3";;
//        String array[] = new String[2];
//        array = frase.split("x");
//        System.out.println(array[0] + " - " + array[1]); 
        List<String> times = new ArrayList<>();
        
        ControlTime.getInstance();

        for (String time : ControlTime.getInstance().values()) {
            times.add(time);
        }
        Collections.shuffle(times);

        ListIterator li = times.listIterator();

        for (ListIterator<String> iterator = times.listIterator(); iterator.hasNext();) {
            String timeA = iterator.next();
            iterator.remove();
            String timeB = iterator.next();
            System.out.println(ControlTime.parseIdentificador(timeA, timeB));
            
            iterator.remove();

        }
    }
}
