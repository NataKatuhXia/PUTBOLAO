/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ControlTime {

    private static Map<String, String> times;

    private ControlTime() {

    }

    public static Map<String, String> getInstance() {
        if (times == null) {
            /*
            Carregar o Json aqui
             */
        }

        return times;
    }

    public static String parseIdentificador(String timeA, String timeB) { // Nome dos times são as Keys
        StringBuilder nomes = new StringBuilder();

        nomes.append(/*times.get(timeA)*/"88").append(/*times.get(timeB)*/ "77");

        return nomes.toString();
    }

    public static String parseTime(String codigo) { // Codigo é o value do Map
        String timeCodigo = null;
        
        for (String time : times.keySet()) {
            if(times.get(time).equals(codigo)){
                timeCodigo = time;
            }
        }

        return timeCodigo;
    }
    
    public static List<String> selectAllTimes(){
        
        List<String> timeLista = new ArrayList<>(); 
        
        for (String time : times.keySet()){
            timeLista.add(time);
        }
        
        return timeLista;
    }
    
    public static void main(String args[]) {

        Map<String, String> example = new HashMap<>();

        /*
              * Vamos adicionar alguns valores a nossa lista
              * */
        example.put("K1", new String("V1"));
        example.put("K2", new String("V2"));
        example.put("K3", new String("V3"));
        example.put("K4", new String("V4"));
        example.put("K5", new String("V5"));
        
        for (String key : example.keySet()) {
            System.out.println(key);
        }

    }
}
