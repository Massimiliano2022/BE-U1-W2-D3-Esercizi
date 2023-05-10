package esercizio2.app.threads;

import java.util.List;

import esercizio1.app.Application;

public class AddRandomNumbersThread extends Thread {

	private int totaleParziale;

	private List<Integer> listaNumeri;

	public int getTotaleParziale() {
		return totaleParziale;
	}

	public void setListaNumeri(List<Integer> listaNumeri) {
		this.listaNumeri = listaNumeri;
	}

	public List<Integer> getListaNumeri() {
		return listaNumeri;
	}

	public AddRandomNumbersThread(List<Integer> listaNumeri) {
		setListaNumeri(listaNumeri);
	}

	@Override
	public void run() {
		for (int numero : listaNumeri) {
			totaleParziale += numero;
			//Application.logger.info("Numero: " + numero);
			//Application.logger.info("Totale Parziale: " + totaleParziale);
		}
	}

}
