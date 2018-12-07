package model.bo;

import java.util.ArrayList;
import java.util.Random;
import model.vo.Jogo;


public class jogoAleatorio {
	
	private String[] times = {"Palmeiras","Bahia","Vitoria","Flamengo","Corinthians"}; //vetor de times.
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>(); //lista de jogos gerados aleatoriamente.

	public jogoAleatorio(int vezes, int max) { 
		for(int i = 0; i < vezes; i++) { // vezes eh o numeros de partidas a serem geradas.
			Jogo partida = new Jogo(); 
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
	
	public void imprimeJogos() {
		for(Jogo obj: this.jogos) {
			System.out.printf("%s %d X %s %d\n",obj.getTimeA(),obj.getPlacarTimeA(),obj.getTimeB(),obj.getPlacarTimeB());
		}
	}
	
	public int returnRandom(int max) {	
		Random rand = new Random();
		return rand.nextInt(max); 
	}

}
