/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ValidationField;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public abstract class Pessoa {

    private String nome;
    private String usuario;
    private String email;
    private String senha;
    private String pontos;

    private boolean contaADM;

    /**
     *
     * @param comando pode ser Consultar, para verificar se existe Login, ou
     * Criar usuário
     * @param nome
     * @param user
     * @param senha
     * @param ponto
     * @return
     */
    protected abstract Pessoa createAccount(String comando, String nome, String user, String senha, String ponto, String email);

    /*
    Caso o usuário mude alguma informação em seu perfil, na tela MyAccount
     */
    public static boolean modifyInformation(String nome, String senha, String usuario) {
        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(senha);
        ValidationField.resultFields.add(usuario);

        if (ValidationField.execute()) {
            PessoaDAO pessoadao = new PessoaDAO();
            pessoadao.updateAccount(nome, senha, usuario, false);
            return true;
        } else {
            return false;
        }
    }

    public static List<Jogo> verifyJogosAbertos() {

        JogoDAO jogodao = new JogoDAO();
        ApostaDAO apostadao = new ApostaDAO();

        List<Jogo> jogosAberto = jogodao.searchAll("Abertos", null);

        for (ListIterator<Jogo> iterator = jogosAberto.listIterator(); iterator.hasNext();) {

            Jogo jogo = iterator.next();
            List<Aposta> apostas = apostadao.readForDesc(jogo.getIdentificador(), "A definir");

            for (Aposta aposta : apostas) {
                if (User.getPessoa().getUsuario().equals(aposta.getUsuario())) {
                    iterator.remove();
                    break;
                }
            }
        }
        return jogosAberto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
