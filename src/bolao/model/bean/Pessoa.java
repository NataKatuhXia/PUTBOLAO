/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ValidationField;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.PessoaDAO;
import bolao.util.Observer;
import java.util.Map;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public abstract class Pessoa {

    private String nome;
    private String usuario;
    private String senha;
    private String pontos;

    private boolean contaADM;

    /**
     *
     * @param comando
     * @param nome
     * @param user
     * @param senha
     * @return
     */
    protected abstract Pessoa createAccount(String comando, String nome, String user, String senha, String ponto);

    public static boolean modifyInformation(String nome, String senha, String usuario) {
        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(senha);
        ValidationField.resultFields.add(usuario);

        if (ValidationField.execute()) {
            PessoaDAO pessoadao = new PessoaDAO();
            pessoadao.updateAccount(nome, senha, usuario);
            return true;
        } else {
            return false;
        }

    }

    public void setContaADM(boolean contaADM) {
        this.contaADM = contaADM;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getPontos() {
        return pontos;
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
