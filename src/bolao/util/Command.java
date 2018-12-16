/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

import bolao.model.bean.Administrador;
import bolao.model.bean.Jogo;

/**
 * Classe para implementação do Padrão Command
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public interface Command {

    /**
     * Método que é chamado para realizar uma sequência de passos que a classe
     * deverá realizar
     *
     * @see Jogo
     * @see Administrador
     * @return o valor dos resultados Gerados
     */
    public String execute();

}
