/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ControlTime;
import static bolao.util.GetProperties.PROP;
import bolao.util.ValidationField;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Apostador extends Pessoa {

    private Aposta aposta;

    @Override
    public Pessoa createAccount(String comando, String nome, String user, String senha, int pontos, String email) {

        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(user);
        ValidationField.resultFields.add(senha);
        ValidationField.resultFields.add(email);

        if (ValidationField.execute()) {

            Pessoa pessoa = new Apostador();
            pessoa.setNome(nome);
            pessoa.setUsuario(user);
            pessoa.setSenha(senha);
            pessoa.setPontos(pontos);
            pessoa.setEmail(email);
            pessoa.setContaADM(false);

            if (comando.equals("Cadastro")) {
                PessoaDAO pessoadao = new PessoaDAO();
                pessoadao.create(pessoa);
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
        pessoadao.updateAposta("Realizar aposta", usuario.getUsuario(), Integer.parseInt(PROP.getProperty("PONTOS_APOSTA")));
        jogodao.update("Realizar aposta", aposta.getIdentificador());

    }

    public static List<Jogo> verifyResult(String data) {

        JogoDAO jogodao = new JogoDAO();
        List<Jogo> jogos = jogodao.searchAll("Fechado", data);

        return jogos;
    }

    public static Map<Aposta, Integer> viewListApostas(String timeA, String timeB) {
        ApostaDAO apostadao = new ApostaDAO();

        List<Aposta> apostas = apostadao.readForDesc(ControlTime.parseIdentificador(timeA, timeB), "A defnir");
        Map<Aposta, Integer> combinacao = new HashMap<>();

        int contador = 0;
        if (!apostas.isEmpty()) {
            String placar = apostas.get(0).getPalpite();
            for (int i = 0; i < apostas.size(); i++) {

                if (apostas.get(i).getPalpite().equals(placar)) {
                    contador++;
                } else {
                    combinacao.put(apostas.get(i - 1), contador);
                    placar = apostas.get(i).getPalpite();
                    contador = 1;
                }

                if (i == apostas.size() - 1) {
                    combinacao.put(apostas.get(i), contador);
                }
            }
        }

        return combinacao;
    }
}
