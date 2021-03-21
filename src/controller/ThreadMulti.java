package controller;

import java.util.concurrent.Semaphore;

public class ThreadMulti extends Thread {
	private int idThread;
	private Semaphore semaforo;

	public ThreadMulti(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		Calculo();
		try {
			semaforo.acquire();
			Transacao();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		Calculo();
		try {
			semaforo.acquire();
			Transacao();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

	private void Calculo() {

		int tempo;

		if (idThread % 3 == 1) {
			try {
				System.out.println("#Id -> " + idThread + " -> Realizando Cálculos");
				tempo = (int) ((Math.random() * 801) + 200);
				// System.out.println("#Id -> "+idThread+ " ->Tempo estimado " + tempo+"ms");
				sleep(tempo);
				realizaCalculo();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (idThread % 3 == 2) {
			try {
				System.out.println("#Id -> " + idThread + " -> Realizando Cálculos");
				tempo = (int) ((Math.random() * 1001) + 500);
				sleep(tempo);
				realizaCalculo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (idThread % 3 == 0) {
			try {
				System.out.println("#Id -> " + idThread + " -> Realizando Cálculos");
				tempo = (int) ((Math.random() * 1001) + 1000);
				sleep(tempo);
				realizaCalculo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Nao atende os requisitos");
		}
	}

	private void Transacao() {

		if (idThread % 3 == 1) {
			try {
				System.out.println("#Id -> " + idThread + " ->Iniciando transações no BD");
				sleep(1000);
				realizaTransacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (idThread % 3 == 0 || idThread % 3 == 2) {
			try {
				System.out.println("#Id -> " + idThread + " ->Iniciando transações no BD");
				sleep(1500);
				realizaTransacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Nao atende aos requisitos");
		}

	}

	private void realizaCalculo() {

		int var1 = 0, var2 = 0, var3 = 0, res = 0;

		for (int i = 1; i <= 100; i++) {
			var1 = 10 * i;
			var2 = 25 * i;
			var3 = 50 * i;
			res = var1 + var2 + var3;
		}
		System.out.println("#Id -> " + idThread + " -> Calculos realizados com sucesso.");
	}

	private void realizaTransacao() {

		int[] bd = new int[10];

		for (int i = 0; i < 10; i++) {
			bd[i] = i;
		}
		for (int i = 0; i < 9; i++) {
			bd[i] = bd[i] * 2 * bd[i + 1];
		}

		System.out.println("#Id -> " + idThread + " ->Transações realizadas com sucesso");

	}
}
