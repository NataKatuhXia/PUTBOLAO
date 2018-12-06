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
public class Administrador extends Pessoa {

    @Override
    public Pessoa build(String nome, String user, String senha) {
        Pessoa pessoa = new Administrador();

        pessoa.setNome(nome);
        pessoa.setUsuario(user);
        pessoa.setSenha(senha);
        pessoa.setContaADM(true);

        return pessoa;
    }

    private void deleteBolao() {

    }

    private void deleteUser() {

    }

    private void generateResult() {

    }

}
