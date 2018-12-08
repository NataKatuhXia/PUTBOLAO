/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ControlBolao;
import bolao.controler.ControlTime;
import bolao.controler.GetProperties;
import bolao.controler.ValidationField;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Administrador extends Pessoa {

    @Override
    public Pessoa createAccount(String comando, String nome, String user, String senha, String pontos) {

        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(user);
        ValidationField.resultFields.add(senha);
        ValidationField.resultFields.add(pontos);

        if (new ValidationField().execute()) {
            Pessoa pessoa = new Administrador();

            pessoa.setNome(nome);
            pessoa.setUsuario(user);
            pessoa.setSenha(senha);
            pessoa.setPontos(pontos);
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
            GetProperties.store("PONTUACAO_INICIAL_USER", permissoes.get("pontos_inicial"), "Valor de pontos para novos usuarios");

            for (String key : permissoes.keySet()) {
                permissoes.remove(key);
            }

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

        Jogo jogo = jogodado.searchJogo("Gerar Resultado", idJogo);
        ControlBolao bolao = new ControlBolao(jogo);

        jogo.setResultado(bolao.execute());

        jogodado.update(jogo);

        Partida partida = new Partida(jogo.getIdentificador(), jogo.getResultado());
        bolao.setMeasurements(partida);

    }

    private void generareAllResult() {
        JogoDAO jogodado = new JogoDAO();

        List<Jogo> jogos = jogodado.searchAll("Gerar resultados totais");

        for (Jogo jogo : jogos) {
            ControlBolao bolao = new ControlBolao(jogo);

            jogo.setResultado(bolao.execute());

            jogodado.update(jogo);

            Partida partida = new Partida(jogo.getIdentificador(), jogo.getResultado());
            bolao.setMeasurements(partida);
        }

    }

    private boolean generateNewPartidas() {

        if (new ControlBolao().generatePartidas()) {

            List<String> times = new ArrayList<>();

            for (String time : ControlTime.getInstance().values()) {
                times.add(time);
            }
            Collections.shuffle(times);

            for (ListIterator<String> iterator = times.listIterator(); iterator.hasNext();) {
                String timeA = iterator.next();
                iterator.remove();
                String timeB = iterator.next();
                iterator.remove();
                String identificador = ControlTime.parseIdentificador(timeA, timeB);

                Jogo jogo = new Jogo(identificador, 0, "", "");

                Date data = new Date();
                DateFormat formataData = DateFormat.getDateInstance();
                jogo.setData(formataData.format(data));

                JogoDAO jogadao = new JogoDAO();
                jogadao.create(jogo);
            }
            JOptionPane.showMessageDialog(null, "Jogos criados com sucesso!");
        }

        return new ControlBolao().generatePartidas();

        /* modelar mais aqui */
    }

    public static void main(String[] args) {
        Administrador usuariodao = new Administrador();

        usuariodao.generateResult("2018");
//        User.getInstance("rafael", "123");
//        
//       Jogo jogo = new Jogo("8877", 2, "", "");
//
//        JogoDAO jogodao = new JogoDAO();
//        
//        jogodao.create(jogo);
//
//        usuariodao.generareAllResult();
//
//        String teste = "Texte1 x Teste2";
//        String[] array = teste.split(" x ");
//        System.out.println(array[0]);
    }
}
