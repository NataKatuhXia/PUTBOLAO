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
import java.util.GregorianCalendar;
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
    public Pessoa createAccount(String comando, String nome, String user, String senha, int pontos, String email) {

        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(user);
        ValidationField.resultFields.add(senha);
        ValidationField.resultFields.add(String.valueOf(pontos));
        ValidationField.resultFields.add(email);

        if (ValidationField.execute()) {
            Pessoa pessoa = new Administrador();

            pessoa.setNome(nome);
            pessoa.setUsuario(user);
            pessoa.setSenha(senha);
            pessoa.setPontos(pontos);
            pessoa.setEmail(email);
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

        if (ValidationField.execute()) {
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

            for (String key : permissoes.values()) {
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

        apostas = apostadao.readForDesc(idJogo, "A definir");

        for (Aposta aposta : apostas) {
            apostadao.delete(aposta.getIdentificador());
        }

    }

    private void deleteUser(Pessoa pessoa) {

        PessoaDAO dao = new PessoaDAO();
        dao.delete(pessoa.getUsuario());

    }

    public static void generateResult(String idJogo) {

        JogoDAO jogodado = new JogoDAO();

        Jogo jogo = jogodado.searchJogo("Gerar Resultado", idJogo);
        ControlBolao bolao = new ControlBolao(jogo);

        jogo.setResultado(bolao.execute());

        jogodado.update(jogo);

        Partida partida = new Partida(jogo.getIdentificador(), jogo.getResultado());
        bolao.setMeasurements(partida);

    }

    public static List<Jogo> generareAllResult() {
        JogoDAO jogodado = new JogoDAO();

        DateFormat formataData = DateFormat.getDateInstance();
        GregorianCalendar gc = new GregorianCalendar();

        gc.add(GregorianCalendar.DATE, 0);
        Date data = gc.getTime();

        List<Jogo> jogos = jogodado.searchAll("Todos", formataData.format(data));
        if (!jogos.isEmpty()) {
            int cont = 0;
            for (Jogo jogo : jogos) {
                ControlBolao bolao = new ControlBolao(jogo);

                jogo.setResultado(bolao.execute());

                jogodado.update(jogo);

                Partida partida = new Partida(jogo.getIdentificador(), jogo.getResultado());
                bolao.setMeasurements(partida);
                cont++;
            }

            JOptionPane.showMessageDialog(null, "Jogos atualizados com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Não há partidas que irão ocorrer hoje!");
        }
        return jogos;
    }

    public static boolean generateNewPartidas() {

        if (new ControlBolao().generatePartidas()) {

            List<String> times = new ArrayList<>();

            for (String time : ControlTime.getInstance().values()) {
                times.add(time);
            }
            Collections.shuffle(times);

            int cont = 0;
            int day = 1;

            for (ListIterator<String> iterator = times.listIterator(); iterator.hasNext();) {
                String timeA = iterator.next();
                iterator.remove();
                String timeB = iterator.next();
                iterator.remove();
                String identificador = ControlTime.parseIdentificador(timeA, timeB);

                Jogo jogo = new Jogo(identificador, 0, "", "");

                DateFormat formataData = DateFormat.getDateInstance();
                GregorianCalendar gc = new GregorianCalendar();
                if (cont < 5) {
                    gc.add(GregorianCalendar.DATE, day);
                    cont++;
                } else {
                    day++;
                    cont = 0;
                    gc.add(GregorianCalendar.DATE, day);

                }

                Date data = gc.getTime();

                jogo.setData(formataData.format(data));

                JogoDAO jogadao = new JogoDAO();
                jogadao.create(jogo);
            }

            return true;
        } else {
            return false;
        }
    }
}
