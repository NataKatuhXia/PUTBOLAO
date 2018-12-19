/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao.controler;

import static bolao.util.GetProperties.PROP;
import bolao.model.bean.Aposta;
import bolao.model.bean.Jogo;
import bolao.model.bean.Partida;
import bolao.model.bean.Pessoa;
import bolao.model.dao.ApostaDAO;
import bolao.model.dao.JogoDAO;
import bolao.model.dao.PessoaDAO;
import bolao.util.Subject;
import bolao.util.Command;
import bolao.connection.MailApp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Classe para controlar a maioria das Operações do Bolão Implementa duas
 * interfaces para utilizar os Padrões Observer e Command
 *
 * @author RAFAELDEOLIVEIRABAHI
 */
public class ControlBolao implements Subject, Command {

    private static final String MAX_GOLS = PROP.getProperty("MAX_GOLS");
    List<Pessoa> pessoas = new ArrayList<>();

    private Jogo jogo;
    private ArrayList observers;

    /**
     * Inicializa a lista de Observadores
     */
    public ControlBolao() {
        observers = new ArrayList<>();
    }

    public ControlBolao(Jogo jogo) {
        observers = new ArrayList<>();
        this.jogo = jogo;
    }

    /**
     * Registra o Usuário na lista de Observadores
     *
     * @param usuario, o login do Usuário
     */
    @Override
    public void registerObserver(String usuario) {
        observers.add(usuario);
    }

    /**
     * Remove o Usuário da lista de Observadores
     *
     * @param usuario
     */
    @Override
    public void removeObserver(String usuario) {

        int i = observers.indexOf(usuario);
        if (i >= 0) {
            observers.remove(usuario);
        }
    }

    /**
     * Notifica os Usuários que apostaram nos determinados Jogos, enviando
     * email, e caso o usuário tenha acertado o palpite acrescenta os pontos ao
     * usuario
     */
    @Override
    public void notifyObservers() {

        MailApp.sendMessages(pessoas);
        pessoas.removeAll(pessoas);

        int pontos = observers.size() / Integer.parseInt(PROP.getProperty("PONTOS_VITORIA"));
        if (pontos < 3) {
            pontos = 3;
        }
        for (int i = 0; i < observers.size(); i++) {
            PessoaDAO pessoa = new PessoaDAO();
            pessoa.updateAposta("vencendor", observers.get(i).toString(), pontos);
            removeObserver(observers.get(i).toString());
        }
    }

    /**
     * Método faz a separação dos perdedores e ganhadores do Jogos
     *
     * @param jogo , o identificador do jogo que ele deverá realizar essa
     * separação
     * @param placar , o placar do jogo para que ele seja utilizado como Filtro
     */
    public void measurementsChanged(String jogo, String placar) {

        ApostaDAO apostadao = new ApostaDAO();

        List<Aposta> apostas = apostadao.read("Todos", jogo, placar);

        for (Aposta aposta : apostas) {
            if (String.valueOf(aposta.getPalpite()).equals(placar)) {
                aposta.setStatus("Venceu");
                registerObserver(aposta.getUsuario());
            } else {
                aposta.setStatus("Perdeu");
            }
            apostadao.update(aposta);
            pessoas.add(PessoaDAO.validationLogin(aposta.getUsuario()));
        }

        notifyObservers();
    }

    public void setMeasurements(Partida partida) {
        measurementsChanged(partida.getJogo(), partida.getPlacar());
    }

    /**
     * Implementação do Padrão Command
     *
     * @return , retorna o placar desses jogos
     */
    @Override
    public List<Jogo> execute() {

        JogoDAO jogodado = new JogoDAO();

        DateFormat formataData = DateFormat.getDateInstance();
        GregorianCalendar gc = new GregorianCalendar();

        gc.add(GregorianCalendar.DATE, 0);
        Date data = gc.getTime();

        List<Jogo> jogos = jogodado.searchAll("Todos", formataData.format(data));
        if (!jogos.isEmpty()) {

            for (Jogo jogo : jogos) {
                ControlBolao bolao = new ControlBolao(jogo);

                jogo.setResultado(generatePlacar());

                jogodado.update(jogo);

                Partida partida = new Partida(jogo.getIdentificador(), jogo.getResultado());
                bolao.setMeasurements(partida);

            }

            JOptionPane.showMessageDialog(null, "Jogos atualizados com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Não há partidas que irão ocorrer hoje!");
        }
        return jogos;

    }

    /**
     * Gerar placar dos jogos de forma aleatória
     *
     * @return , o valor do placar de um time
     */
    private String generatePlacar() {
        Random gerador = new Random();

        int valorA = gerador.nextInt(Integer.parseInt(MAX_GOLS));
        int valorB = gerador.nextInt(Integer.parseInt(MAX_GOLS));

        String resultado = String.valueOf(valorA) + "x";
        resultado += String.valueOf(valorB);

        return resultado;
    }

    /**
     * Gerar as partidas da rodada
     *
     * @return , boolean caso as partidas forem geradas, se estiver partidas sem
     * resultado gerado, o Administrador não consegue gerar
     */
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

    /**
     * Validação das apostas realizadas pelos usuários
     *
     * @param identificador , identificador da partida
     * @param usuario , o login do usuário para verificar se ele realizou já
     * aposta naquele jogo
     * @return , Booleano para caso essa aposta seja validada
     */
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
}
