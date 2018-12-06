/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class User {

    private static Pessoa pessoa;
    private volatile static User usuario;

    private User() {

    }

    public static void setUsuario(Pessoa pessoa) {
        User.pessoa = pessoa;
        
        usuario = new User();
    }

    public static User getInstance() {

        if (usuario == null) {
            usuario = new User();
        }

        return usuario;
    }
}
