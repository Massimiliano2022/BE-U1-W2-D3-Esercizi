package esercizio1.app.threads;

import esercizio1.app.Application;

public class MyThread implements Runnable {

	private String simbolo;

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public MyThread(String simbolo) {
		setSimbolo(simbolo);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Application.logger.info(this.simbolo);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Application.logger.error(e.getMessage());
			}
		}
	}

}
