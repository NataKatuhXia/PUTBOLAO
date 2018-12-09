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

    private String jogo;
    private String placar;

    public Partida(String jogo, String placar) {
        this.jogo = jogo;
        this.placar = placar;
    }

    public String getJogo() {
        return jogo;
    }

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    public String getPlacar() {
        return placar;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }

}
