/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.util;

import bolao.model.bean.Partida;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public interface Observer {

    public void update(Partida partida);
}
