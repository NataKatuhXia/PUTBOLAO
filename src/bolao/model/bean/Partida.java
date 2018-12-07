/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import java.util.List;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Partida {

    static List<Partida> partidas;

    private String jogo;
    private String placarA;
    private String placarB;

    public Partida(String jogo, String placar) {
        this.jogo = jogo;

        String array[] = new String[2];
        array = placar.split("x");
        this.placarA = array[0];
        this.placarB = array[1];
    }

    public String getJogo() {
        return jogo;
    }

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    public String getPlacarA() {
        return placarA;
    }

    public void setPlacarA(String placarA) {
        this.placarA = placarA;
    }

    public String getPlacarB() {
        return placarB;
    }

    public void setPlacarB(String placarB) {
        this.placarB = placarB;
    }

}
