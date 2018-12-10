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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.swing.JOptionPane;

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
//    JOptionPane.showMessageDialog(null, "As informações foram atualizadas, as alterações serão carregadas no próximo acesso");
//        List<String> times = new ArrayList<>();
//        
//        ControlTime.getInstance();
//
//        for (String time : ControlTime.getInstance().values()) {
//            times.add(time);
//        }
//        Collections.shuffle(times);
//
//        ListIterator li = times.listIterator();
//
//        for (ListIterator<String> iterator = times.listIterator(); iterator.hasNext();) {
//            String timeA = iterator.next();
//            iterator.remove();
//            String timeB = iterator.next();
//            System.out.println(ControlTime.parseIdentificador(timeA, timeB));
//            
//            iterator.remove();
//
//        }
//        Date data = new Date();
//        DateFormat formataData = DateFormat.getDateInstance();
//        GregorianCalendar gc = new GregorianCalendar();
//        gc.add(GregorianCalendar.DATE, 1);
//        
//        data = gc.getTime();
//        
//        System.out.println(formataData.format(data));
//        int cont = 0;
//        int day = 0;
//
//        for (int i = 0; i < 10; i++) {
//            DateFormat formataData = DateFormat.getDateInstance();
//            GregorianCalendar gc = new GregorianCalendar();
//            if (cont < 5) {
//                gc.add(GregorianCalendar.DATE, day);
//                cont++;
//            } else {
//                day++;
//                cont = 0;
//                gc.add(GregorianCalendar.DATE, day);
//
//            }
//
//            Date data = gc.getTime();
//
//            System.out.println(formataData.format(data));
//        }
//        Map<String, String> permissoes = new HashMap<>();
//
//        permissoes.put("driver", "01");
//        permissoes.put("url", "02");
//        permissoes.put("usuarioBanco", "03");
//
//        for (String key : permissoes.values()) {
//            permissoes.remove(key);
//        }

    }
}
