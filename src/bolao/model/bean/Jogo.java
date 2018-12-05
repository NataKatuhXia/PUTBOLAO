/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.util.Command;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Jogo {

    Command slot;

    public Jogo() {

    }

    public void setCommand(Command command) {
        slot = command;
        slot.execute();
    }

}
