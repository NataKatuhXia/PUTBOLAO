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
public class Apostador extends Pessoa {

    private Aposta aposta;

    @Override
    public Pessoa build(String nome, String user, String senha) {
        Pessoa pessoa = new Apostador();
        pessoa.setNome(nome);
        pessoa.setUsuario(user);
        pessoa.setSenha(senha);

        pessoa.setContaADM(false);

        return pessoa;
    }

    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

}
