/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import static bolao.controler.GetProperties.PROP;
import bolao.model.bean.Aposta;
import bolao.model.bean.Jogo;
import bolao.model.bean.Partida;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import bolao.util.Subject;
import bolao.util.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ControlBolao implements Subject, Command {

    private static final String MAX_GOLS = PROP.getProperty("MAX_GOLS");

    private Jogo jogo;
    private ArrayList observers;

    public ControlBolao() {
        observers = new ArrayList<>();
    }

    public ControlBolao(Jogo jogo) {
        observers = new ArrayList<>();
        this.jogo = jogo;
    }

    @Override
    public void registerObserver(String usuario) {
        observers.add(usuario);
    }

    @Override
    public void removeObserver(String usuario) {

        int i = observers.indexOf(usuario);
        if (i >= 0) {
            observers.remove(usuario);
        }
    }

    @Override
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            PessoaDAO pessoa = new PessoaDAO();
            pessoa.updateAposta("vencendor", observers.get(i).toString());
            removeObserver(observers.get(i).toString());
        }
    }

    public void measurementsChanged(String jogo, String placar) {

        List<Aposta> apostas = new ArrayList<>();

        ApostaDAO apostadao = new ApostaDAO();
        apostas = apostadao.read("Todos", jogo, placar);

        for (Aposta aposta : apostas) {
            if (String.valueOf(aposta.getPalpite()).equals(placar)) {
                aposta.setStatus("Venceu");
                registerObserver(aposta.getUsuario());
            } else {
                aposta.setStatus("Perdeu");
            }
            apostadao.update(aposta);

        }

        notifyObservers();
    }

    public void setMeasurements(Partida partida) {
        measurementsChanged(partida.getJogo(), partida.getPlacar());
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

    public boolean generatePartidas() {
        JogoDAO jogodado = new JogoDAO();

        boolean gerar = true;
        List<Jogo> jogos = jogodado.searchAll("Todos", null);

        if (!jogos.isEmpty()) {
            for (Jogo jogo : jogos) {

                if (null == jogo.getResultado()) {
                    gerar = false;
                    break;
                }
            }
        }
        return gerar;
    }

    public static boolean validationAposta(String identificador, String usuario) {

        ApostaDAO apostadao = new ApostaDAO();

        boolean result = true;

        List<Aposta> apostas = new ArrayList<>();
        apostas = apostadao.readForDesc(identificador, "A definir");

        for (Aposta aposta : apostas) {
            if (aposta.getUsuario().equals(usuario)) {
                result = false;
                break;
            }
        }

        return result;
    }

//    public static void main(String[] args) {
//        User.getInstance("teste", "321");
//        new TelaCriacaoAposta().setVisible(true);
//        JogoDAO jogodado = new JogoDAO();
//        Jogo aux = new Jogo("Test", 0, "", "");
//        jogodado.create(aux);
//
//        boolean gerar = true;
//        List<Jogo> jogos = jogodado.searchAll("Todos");
//
//        for (Jogo jogo : jogos) {
//            if (null == jogo.getResultado()) {
//                System.out.println("salve");
//            } else {
//                System.out.println("Teste");
//            }
//        }
//    }
}
