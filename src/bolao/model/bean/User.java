/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.controler.ValidationField;
import bolao.model.dao.PessoaDAO;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class User {

    private static Pessoa pessoa;
    private static boolean acesso;
    private volatile static User usuario;

    private User(String user, String senha) {

        acesso = true;

        ValidationField.resultFields.add(user);
        ValidationField.resultFields.add(senha);

        PessoaDAO usuariodao = new PessoaDAO();

        Pessoa aux = usuariodao.checkLogin("Consulta", user, senha);

        if ((aux != null) && (new ValidationField().execute())) {

            User.setUsuario(aux);

        } else {
            acesso = false;
        }

    }

    private static void setUsuario(Pessoa pessoa) {
        User.pessoa = pessoa;

    }

    public static Pessoa getPessoa() {

        return pessoa;

    }

    public static User getInstance(String user, String senha) {

        if (usuario == null) {
            usuario = new User(user, senha);
            if (!acesso) {
                usuario = null;
            }
        }

        return usuario;
    }

    public static void deslogarUser() {
        pessoa = null;
        usuario = null;
    }

}
