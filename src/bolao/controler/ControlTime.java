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
 * Responsável por controlar a maioria das operações dos Times
 *
 * z
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ControlTime {

    public static Map<String, String> times = new HashMap<>();

    /**
     * Contrutor Privado para não carregar Novamente os Jsons armazenados no
     * Projeto. Padrão Singleton
     *
     */
    private ControlTime() {

        if (times.isEmpty()) {
            loadLista();
        }

    }

    /**
     * Caso ainda não tenha sido instanciada ela realiza o carregamento dos
     * times através do Json
     *
     * @see LoadUtil
     */
    private void loadLista() {
        Equipe equipe = Equipe.build();

        List<Equipe> equipes = equipe.getEquipes();

        for (Equipe aux : equipes) {
            times.put(aux.getIdentificador(), aux.getEquipe());
        }
    }

    /**
     * Método que tem uma função parecida com o GetInstance(), do padrão
     * Singleton, e ocorre para não deixar a instânciação mais de uma vez, da
     * mesma classe
     *
     * @return O Map dos Times carregados no Json
     * @see LoadUtil
     */
    public static Map<String, String> getInstance() {
        if (times.isEmpty()) {
            new ControlTime().loadLista();
        }

        return times;
    }

    /**
     * Método para transformar o jogo em identificadores
     *
     * @param timeA , o primeiro time que irá jogar
     * @param timeB , o segundo time que irá jogar
     * @return , o identificador da partida, referente ao jogo de TimeA x TimeB,
     * com a seguinte estrutura IDTimeA + IDTimeB
     */
    public static String parseIdentificador(String timeA, String timeB) { // Nome dos times são as Keys

        String equipeA = null;
        String equipeB = null;

        for (String time : ControlTime.times.keySet()) {
            if (times.get(time).equals(timeA)) {

                equipeA = time;

            } else if (times.get(time).equals(timeB)) {

                equipeB = time;

            }
        }

        return equipeA + equipeB;
    }

    /**
     * Transforma o código identificador em Jogos
     *
     * @param codigo, recebe o código da jogo para retornar o nome dos times
     * participantes daquela partida
     * @return o nome dos times que estão se enfrentando, TimeA x TimeB
     */
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

    /**
     * Metodo para coletar todos os times que estão no sistema
     *
     * @return , a lista de Times do Sistema
     */
    public static List<String> selectAllTimes() {

        List<String> timeLista = new ArrayList<>();

        for (String time : times.keySet()) {
            timeLista.add(time);
        }

        return timeLista;
    }

    /**
     * Caso o Administrador do Sistema tenha vontade de alterar o nome dos
     * Times, este método é responsável por isso, e ao final grava o novo Json
     * de Times
     *
     * @see LoadUtil
     *
     * @param times, recebe a lista de times já com as atualizações
     */
    public static void updateTimes(List<Equipe> times) {

        try {
            LoadUtil.writerFile("Json/ListaTimes", times);
        } catch (IOException ex) {
            Logger.getLogger(ControlTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
