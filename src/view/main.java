package view;

import java.util.concurrent.Semaphore;

import controller.ThreadMulti;

public class main {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 1; i<22; i++) {
			Thread Tm = new ThreadMulti(i, semaforo);
			Tm.start();
		}
		
	}

}
