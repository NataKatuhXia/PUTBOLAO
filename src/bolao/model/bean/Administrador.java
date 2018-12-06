/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.GetProperties;
import bolao.controler.ValidationField;
import java.util.Map;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Administrador extends Pessoa {

    @Override
    public Pessoa createAccount(String nome, String user, String senha) {

        ValidationField.resultFields.add(nome);
        ValidationField.resultFields.add(user);
        ValidationField.resultFields.add(senha);

        if (new ValidationField().execute()) {
            Pessoa pessoa = new Administrador();

            pessoa.setNome(nome);
            pessoa.setUsuario(user);
            pessoa.setSenha(senha);
            pessoa.setContaADM(true);

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

    private void deleteUser() {

    }

    private void generateResult() {

    }

}
