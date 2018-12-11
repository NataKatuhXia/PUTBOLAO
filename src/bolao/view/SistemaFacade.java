/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.view;

import bolao.controler.ControlTime;
import bolao.model.bean.User;
import bolao.view.adm.TelaPrincipalAdministrador;
import bolao.view.apostador.TelaPrincipalApostador;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class SistemaFacade {

    public static void inicializarSystem() {

        // Carregar os times no Sistema através da leitura do Json
        ControlTime.getInstance();

        // Selecionar a tela que será apresentada para o usuario definida pela conta dele
        visibleTela();

    }

    private static void visibleTela() {

        if (User.getPessoa().isContaADM()) {

            new TelaPrincipalAdministrador().setVisible(true);

        } else {

            new TelaPrincipalApostador().setVisible(true);

        }
    }

}
