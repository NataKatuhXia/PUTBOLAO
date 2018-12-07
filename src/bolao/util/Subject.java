/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

import bolao.model.bean.Pessoa;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public interface Subject {

    public void registerObserver(String pessoa);
    
    public void removeObserver(String pessoa);

    public void notifyObservers();

}
