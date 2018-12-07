package executavel;

import java.util.Scanner;

import model.bo.jogoAleatorio;

public class Principal {
	
	public static void main(String[] args) {
		
		int vezes;
		int max;
		Scanner entrada = new Scanner(System.in);
		System.out.printf("Digite numero de partidas a serem geradas: ");
		vezes = entrada.nextInt();
		System.out.printf("Digite placar maximo de um time: ");
		max = entrada.nextInt();
		entrada.close();
		jogoAleatorio partidas = new jogoAleatorio(vezes,max);
		partidas.imprimeJogos();
	}

}
