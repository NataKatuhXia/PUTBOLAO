/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.util.Command;
import java.util.Date;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Jogo {

    private String identificador;
    private int apostadores;
    private String resultado;
    private String data;

    Command slot;

    public Jogo() {

    }

    public Jogo(String identificador, int apostadores, String resultado, String data) {
        this.identificador = identificador;
        this.apostadores = apostadores;
        this.resultado = resultado;
        this.data = data;
    }

    public void setCommand(Command command) {
        slot = command;
        slot.execute();
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getApostadores() {
        return apostadores;
    }

    public String getResultado() {
        return resultado;
    }

    public String getData() {
        return data;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setApostadores(int apostadores) {
        this.apostadores = apostadores;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setData(String data) {
        this.data = data;
    }

}
