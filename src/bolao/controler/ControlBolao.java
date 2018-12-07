/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import static bolao.controler.GetProperties.PROP;
import bolao.model.bean.Pessoa;
import bolao.model.bean.Jogo;
import bolao.model.bean.Partida;
import bolao.util.Subject;
import bolao.util.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ControlBolao implements Subject, Command {

    private static final String MAX_GOLS = PROP.getProperty("MAX_GOLS");
    private Partida partida;

    private Jogo jogo;
    private ArrayList observers;
    public static String idPartida;
    private Map<Integer, Integer> placar;

    public ControlBolao() {
        observers = new ArrayList<>();
    }

    public ControlBolao(Jogo jogo) {
        observers = new ArrayList<>();
        this.jogo = jogo;
    }

    @Override
    public void registerObserver(Pessoa pessoa) {
        observers.add(pessoa);
    }

    @Override
    public void removeObserver(Pessoa pessoa) {
        int i = observers.indexOf(pessoa);
        if (i >= 0) {
            observers.remove(pessoa);
            /* 
            Fazer uma consulta no banco trazendo todos os usuarios que fizeram os boloes naquela partida
            Caso o bolao tenha acertado o placar busca todos os apostadores que apostaram nesse bolao e da um update
            Comando DAO trazendo os boloes
            E outro trazendo os apostadores
             */
        }
    }

    @Override
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            Pessoa pessoa = (Pessoa) observers.get(i);
            pessoa.update(partida);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(Partida partidas) {
        measurementsChanged();
    }

    @Override
    public String execute() {

        int y = generatePlacar();
        int x = generatePlacar();

        String resultado = String.valueOf(y) + "x";
        resultado += String.valueOf(x);
        
        return resultado;
//        setMeasurements(generatePartidas());
    }

    private int generatePlacar() {
        Random gerador = new Random();

        int valor = gerador.nextInt(Integer.parseInt(MAX_GOLS));

        return valor;
    }

    private Partida generatePartidas() {

        return new Partida("", "", "");
    }
}
