/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ControlBolao;
import bolao.controler.GetProperties;
import bolao.controler.ValidationField;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import java.text.DateFormat;
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

            return true;
        } else {
            return false;
        }

    }

    private void deleteBolao() {

    }

    private void deleteUser(Pessoa pessoa) {

        PessoaDAO dao = new PessoaDAO();
        dao.delete(pessoa);

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

    }

    private void generareAllResult() {
        JogoDAO jogodado = new JogoDAO();

        List<Jogo> jogos = jogodado.searchAll();

        for (Jogo jogo : jogos) {
            ControlBolao bolao = new ControlBolao(jogo);

            Date data = new Date();
            DateFormat formataData = DateFormat.getDateInstance();

            jogo.setResultado(bolao.execute());
            jogo.setData(formataData.format(data));

            jogodado.update(jogo);
        }

    }

    public static void main(String[] args) {

        Administrador usuariodao = new Administrador();
        Jogo jogo = new Jogo("1903", 2, "", "");
        Jogo jogo2 = new Jogo("1920", 4, "", "");

        JogoDAO jogodao = new JogoDAO();
        jogodao.create(jogo);
        jogodao.create(jogo2);

        usuariodao.generareAllResult();

    }
}
