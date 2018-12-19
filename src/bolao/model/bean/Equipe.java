/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.model.bean;

import bolao.util.LoadUtil;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class Equipe {

    static List<Equipe> equipes;
    private String nome;
    private String identificador;

    public static Equipe build() {
        Equipe equipe = new Equipe();
        List<Equipe> equipes = LoadUtil.loadListObjects("src//Json//ListaTimes", Equipe[].class);
        equipe.setEquipes(equipes);

        return equipe;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public String getEquipe() {
        return nome;
    }

    public void setEquipe(String nome) {
        this.nome = nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public static void main(String[] args) {
        List<Equipe> equipes = LoadUtil.loadListObjects("src/Json/ListaTimes", Equipe[].class);
    }
}
