/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

import bolao.controler.ControlBolao;
import bolao.model.bean.Pessoa;

/**
 * Classe para a implementação do Padrão Observer, esta classe é para o Sujeito
 * da notificação
 *
 * @see ControlBolao, classe que implementa esta interface
 * @author RAFAELDEOLIVEIRABAHI
 */
public interface Subject {

    /**
     * método para registrar os observadores no Sistema
     *
     * @param pessoa, o nome do usuário que será inserido na lista
     */
    public void registerObserver(String pessoa);

    /**
     * Método para remover o usuário da lista de Observadores
     *
     * @param pessoa, o nome do usuário que será retirado.
     */
    public void removeObserver(String pessoa);

    /**
     * Método para notificar os Usuários
     */
    public void notifyObservers();

}
