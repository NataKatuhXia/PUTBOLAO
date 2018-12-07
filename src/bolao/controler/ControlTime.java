/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import bolao.model.bean.Equipe;
import bolao.util.LoadUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ControlTime {

    public static Map<String, String> times = new HashMap<>();

    public ControlTime() {

        if (times.size() == 0) {
            loadLista();
        }

    }

    private void loadLista() {
        Equipe equipe = Equipe.build();

        List<Equipe> equipes = equipe.getEquipes();

        for (Equipe aux : equipes) {
            times.put(aux.getIdentificador(), aux.getEquipe());
        }
    }

    public static Map<String, String> getInstance() {
        if (times == null) {
            new ControlTime().loadLista();
        }

        return times;
    }

    public static String parseIdentificador(String timeA, String timeB) { // Nome dos times são as Keys
        StringBuilder nomes = new StringBuilder();

        nomes.append(times.get(timeA)).append(times.get(timeB));

        return nomes.toString();
    }

    public static String parseTime(String codigo) { // Codigo é o value do Map
        String timeCodigo = null;

        for (String time : times.keySet()) {
            if (times.get(time).equals(codigo)) {
                timeCodigo = time;
            }
        }

        return timeCodigo;
    }

    public static List<String> selectAllTimes() {

        List<String> timeLista = new ArrayList<>();

        for (String time : times.keySet()) {
            timeLista.add(time);
        }

        return timeLista;
    }

    public static void updateTimes(List<Equipe> times) {

        try {
            LoadUtil.writerFile("Json/ListaTimes", times);
        } catch (IOException ex) {
            Logger.getLogger(ControlTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {

//        Map<String, String> example = new HashMap<>();
//
//        /*
//              * Vamos adicionar alguns valores a nossa lista
//              * */
//        example.put("K1", new String("V1"));
//        example.put("K2", new String("V2"));
//        example.put("K3", new String("V3"));
//        example.put("K4", new String("V4"));
//        example.put("K5", new String("V5"));
//
//        for (String key : example.keySet()) {
//            System.out.println(key);
//        }
//        System.out.println(new ControlTime().times.size());
//        for (String time : new ControlTime().times.values()) {
//            System.out.println(time);
//        }

    }
}
