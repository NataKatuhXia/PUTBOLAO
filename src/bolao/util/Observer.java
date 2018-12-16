/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

/**
 * Classe para implementar o padrão Observer no sistema
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public interface Observer {

    /**
     * Classe para dar a atualização na lista de Observadores
     *
     * @param comando, o tipo de Atualização que será feita nos Observadores
     * @param user, o Usuário que recebrá a atualização
     * @param pontos, caso seja uma atualização de aumento ou decremento de
     * pontos, este parâmentro recebe o valor do ponto
     */
    public void updateAposta(String comando, String user, int pontos);
}
