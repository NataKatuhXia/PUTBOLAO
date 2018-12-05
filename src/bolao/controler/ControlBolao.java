/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import static bolao.controler.GetProperties.PROP;
import bolao.model.bean.Pessoa;
import bolao.model.bean.Jogo;
import bolao.util.Subject;
import bolao.util.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ControlBolao implements Subject, Command {

    private static final String MAX_GOLS = PROP.getProperty("MAX_GOLS");
    private Jogo jogo;
    private ArrayList observers;
    public static String idPartida = "0000";
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
        }
    }

    @Override
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            Pessoa pessoa = (Pessoa) observers.get(i);
            pessoa.update(placar);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(String idJogo, Map<Integer, Integer> placar) {
        ControlBolao.idPartida = idJogo;
        this.placar = placar;

    }

    @Override
    public void execute() {
        String identificador = generateIdJogo();
        Map<Integer, Integer> placares = generatePlacar();

        setMeasurements(identificador, placares);

        measurementsChanged();

    }

    private String generateIdJogo() {
        int identificador = Integer.parseInt(ControlBolao.idPartida);
        identificador++;
        ControlBolao.idPartida = String.valueOf(identificador);

        return idPartida;
    }

    private Map<Integer, Integer> generatePlacar() {
        Random gerador = new Random();

        Map<Integer, Integer> placares = new HashMap<>();
        int timeA = gerador.nextInt(Integer.parseInt(MAX_GOLS));
        int timeB = gerador.nextInt(Integer.parseInt(MAX_GOLS));
        placares.put(timeA, timeB);

        return placares;
    }
}
