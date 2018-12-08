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

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Apostador extends Pessoa {

    private Aposta aposta;

    @Override
    public Pessoa createAccount(String comando, String nome, String user, String senha, String pontos) {

        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(user);
        ValidationField.resultFields.add(senha);
        ValidationField.resultFields.add(pontos);

        if (new ValidationField().execute()) {

            Pessoa pessoa = new Apostador();
            pessoa.setNome(nome);
            pessoa.setUsuario(user);
            pessoa.setSenha(senha);
            pessoa.setPontos(pontos);
            pessoa.setContaADM(false);

            if (comando.equals("Cadastro")) {
                PessoaDAO pessoadao = new PessoaDAO();
                pessoadao.create(pessoa);
            } else if (comando.equals("Consulta")){
                
            }

            return pessoa;
        } else {
            return null;
        }

    }

    public static void createAposta(Aposta aposta, Pessoa usuario) {
        ApostaDAO apostadao = new ApostaDAO();
        PessoaDAO pessoadao = new PessoaDAO();
        JogoDAO jogodao = new JogoDAO();
        
        apostadao.create(aposta);
        pessoadao.update("Realizar aposta", usuario.getUsuario());
        jogodao.update("Realizar aposta", aposta.getIdentificador());
        
        
    }

}
