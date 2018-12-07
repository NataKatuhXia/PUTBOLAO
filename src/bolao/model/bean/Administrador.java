/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ControlBolao;
import bolao.controler.GetProperties;
import bolao.controler.ValidationField;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Administrador extends Pessoa {

    @Override
    public Pessoa createAccount(String comando, String nome, String user, String senha) {

        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(user);
        ValidationField.resultFields.add(senha);

        if (new ValidationField().execute()) {
            Pessoa pessoa = new Administrador();

            pessoa.setNome(nome);
            pessoa.setUsuario(user);
            pessoa.setSenha(senha);
            pessoa.setContaADM(true);

            if (comando.equals("Cadastro")) {
                PessoaDAO pessoadao = new PessoaDAO();
                pessoadao.create(pessoa);
            }

            return pessoa;
        } else {
            return null;
        }
    }

    public static boolean modifyPermissions(Map<String, String> permissoes) {

        ValidationField.resultFields.addAll(permissoes.keySet());

        if (new ValidationField().execute()) {
            GetProperties.store("MAX_GOLS", permissoes.get("maximoGols"), "Numero de Gols");
            GetProperties.store("DRIVER_DATE", permissoes.get("driver"), "Local Driver Banco");
            GetProperties.store("URL_DATE", permissoes.get("url"), "URL Banco");
            GetProperties.store("USER_DATE", permissoes.get("usuarioBanco"), "Usuario Banco");
            GetProperties.store("PASSWORD_DATE", permissoes.get("senhaBanco"), "Senha Banco");
            GetProperties.store("QNTD_USER_APOSTA", permissoes.get("userAposta"), "Quantidade usuario aposta");
            GetProperties.store("ADM_APOSTA", permissoes.get("adm_aposta"), "Parametro para Administrador fazer aposta");
            GetProperties.store("PONTOS_VITORIA", permissoes.get("pontos_vitoria"), "Valor de pontos para vencedores");
            GetProperties.store("PONTOS_APOSTA", permissoes.get("pontos_aposta"), "Valor de pontos para realizar uma aposta");

            return true;
        } else {
            return false;
        }

    }

    private void deleteBolao(String idJogo) {
        List<Aposta> apostas = new ArrayList<>();
        ApostaDAO apostadao = new ApostaDAO();

        apostas = apostadao.readForDesc("idJogo");

        for (Aposta aposta : apostas) {
            apostadao.delete(aposta);
        }

    }

    private void deleteUser(Pessoa pessoa) {

        PessoaDAO dao = new PessoaDAO();
        dao.delete(pessoa.getUsuario());

    }

    private void generateResult(String idJogo) {

        JogoDAO jogodado = new JogoDAO();

        Jogo jogo = jogodado.searchJogo(idJogo);
        ControlBolao bolao = new ControlBolao(jogo);

        Date data = new Date();
        DateFormat formataData = DateFormat.getDateInstance();

        jogo.setResultado(bolao.execute());
        jogo.setData(formataData.format(data));

        jogodado.update(jogo);

        Partida partida = new Partida(jogo.getIdentificador(), jogo.getResultado());
        bolao.setMeasurements(partida);

    }

    private void generareAllResult() {
        JogoDAO jogodado = new JogoDAO();

        List<Jogo> jogos = jogodado.searchAll("Gerar resultados totais");

        for (Jogo jogo : jogos) {
            ControlBolao bolao = new ControlBolao(jogo);

            Date data = new Date();
            DateFormat formataData = DateFormat.getDateInstance();

            jogo.setResultado(bolao.execute());
            jogo.setData(formataData.format(data));

            jogodado.update(jogo);

            Partida partida = new Partida(jogo.getIdentificador(), jogo.getResultado());
            bolao.setMeasurements(partida);
        }

    }

    public static void main(String[] args) {

//        Administrador usuariodao = new Administrador();
//        User.getInstance("rafael", "123");
//        
//       Jogo jogo = new Jogo("8877", 2, "", "");
//
//        JogoDAO jogodao = new JogoDAO();
//        
//        jogodao.create(jogo);
//
//        usuariodao.generareAllResult();
    }
}
