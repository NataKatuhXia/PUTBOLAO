/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ControlBolao;
import bolao.util.Observer;
import java.util.Map;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public abstract class Pessoa implements Observer {

    private String nome;
    private String usuario;
    private String senha;
    private String pontos;

    private boolean contaADM;

    private String idJogo;
    private Map<Integer, Integer> placar;

    /**
     *
     * @param nome
     * @param user
     * @param senha
     * @return
     */
    protected abstract Pessoa build(String nome, String user, String senha);

    @Override
    public void update(Map<Integer, Integer> placar) {
        
        this.idJogo = ControlBolao.idPartida;
        this.placar = placar;

        System.out.println("Jogo: " + idJogo + " - " + placar.keySet() + " x " + placar.values());
    }

    public void setContaADM(boolean contaADM) {
        this.contaADM = contaADM;
    }

    public boolean isContaADM() {
        return contaADM;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
