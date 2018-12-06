/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Partida {

    private String jogo;
    private String placarA;
    private String placarB;

    public Partida(String jogo, String placarA, String placarB) {
        this.jogo = jogo;
        this.placarA = placarA;
        this.placarB = placarB;
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
