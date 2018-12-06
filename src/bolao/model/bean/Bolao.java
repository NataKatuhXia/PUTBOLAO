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
public class Bolao {

    private String identificador;
    private int apostadores;

    public Bolao(String identificador, int apostadores) {
        this.identificador = identificador;
        this.apostadores = apostadores;
    }

    public int getApostadores() {
        return apostadores;
    }

    public void setApostadores(int apostadores) {
        this.apostadores = apostadores;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

}
