package model.bo;

import java.util.ArrayList;
import java.util.Random;
import model.vo.Jogo;


public class jogoAleatorio {
	
	private String[] times = {"Palmeiras","Bahia","Vitoria","Flamengo","Corinthians","Gremio","Fluminense"}; //vetor de times.
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>(); //lista de jogos gerados aleatoriamente.
	private Jogo partida;

	public jogoAleatorio(int vezes, int max) { 
		for(int i = 0; i < vezes; i++) { // vezes eh o numeros de partidas a serem geradas.
			partida = new Jogo(); 
			int A = returnRandom(times.length);
			partida.setTimeA(times[A]);
			int B = A;
			while(B == A) { // evita um time duplicado em ambos os times A e B.
				B = returnRandom(times.length);
			}
			partida.setTimeB(times[B]);
			partida.setPlacarTimeA(returnRandom(max)); //max é o placar maximo por time. 
			partida.setPlacarTimeB(returnRandom(max));
			jogos.add(partida);
		}  
	}
	
	public jogoAleatorio(int max) {  //versao de torneio.
		for(int a = 0; a < times.length; a++) {
			for(int b = a+1; b < times.length; b++) {
				partida = new Jogo();
				partida.setTimeA(times[a]);
				partida.setTimeB(times[b]);
				partida.setPlacarTimeA(returnRandom(max)); 
				partida.setPlacarTimeB(returnRandom(max));
				jogos.add(partida);
			}
		}  
	}
	
	public void imprimeJogos() {
		for(Jogo obj: this.jogos) {
			System.out.printf("%s %d X %d %s\n",obj.getTimeA(),obj.getPlacarTimeA(),obj.getPlacarTimeB(),obj.getTimeB());
		}
	}
	
	public int returnRandom(int max) {	
		Random rand = new Random();
		return rand.nextInt(max); 
	}

}
