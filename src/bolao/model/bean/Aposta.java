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
public class Aposta {

    private String usuario;
    private String identificador;
    private int placarA;
    private int plcarB;

    public Aposta(String usuario, String identificador, int placarA, int plcarB) {
        this.usuario = usuario;
        this.identificador = identificador;
        this.placarA = placarA;
        this.plcarB = plcarB;
    }

    public Aposta() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getPlacarA() {
        return placarA;
    }

    public void setPlacarA(int placarA) {
        this.placarA = placarA;
    }

    public int getPlacarB() {
        return plcarB;
    }

    public void setPlcarB(int plcarB) {
        this.plcarB = plcarB;
    }

}
