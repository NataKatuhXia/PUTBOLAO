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

    private ControlTime() {

        if (times.isEmpty()) {
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
        if (times.isEmpty()) {
            new ControlTime().loadLista();
        }

        return times;
    }

    public static String parseIdentificador(String timeA, String timeB) { // Nome dos times são as Keys
        StringBuilder nomes = new StringBuilder();

        String equipeA = null;
        String equipeB = null;

        for (String time : new ControlTime().times.keySet()) {
            if (times.get(time).equals(timeA)) {

                equipeA = time;

            } else if (times.get(time).equals(timeB)) {

                equipeB = time;

            }
        }

        return equipeA + equipeB;
    }

    public static String parseTime(String codigo) { // Codigo é o value do Map
        String timeCodigo = null;
        String timeA = (codigo.substring(0, 2));
        String timeB = (codigo.substring(2, 4));

        for (String time : times.keySet()) {
            if (time.equals(timeA)) {
                timeCodigo = times.get(time);
                break;
            }
        }

        for (String time : times.keySet()) {
            if (time.equals(timeB)) {
                timeCodigo += " x " + times.get(time);
                break;
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
}
