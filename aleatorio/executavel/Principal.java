package executavel;

import java.util.Scanner;

import model.bo.jogoAleatorio;

public class Principal {
	
	public static void main(String[] args) {
		
		int vezes;
		int max;
		jogoAleatorio partidas;
		Scanner entrada = new Scanner(System.in);
		System.out.printf("Digite numero de partidas a serem geradas (0 para torneio): ");
		vezes = entrada.nextInt();
		System.out.printf("Digite placar maximo de um time: ");
		max = entrada.nextInt();
		entrada.close();
		if(vezes>0)
			partidas = new jogoAleatorio(vezes,max);
		else
			partidas = new jogoAleatorio(max);
		partidas.imprimeJogos();
	}

}
