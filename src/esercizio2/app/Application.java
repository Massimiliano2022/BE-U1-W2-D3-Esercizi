package esercizio2.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import esercizio2.app.threads.AddRandomNumbersThread;

public class Application {

	public static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		int totale;

		List<Integer> numeriCasuali = generaListaNumeriCasuali();

		AddRandomNumbersThread t0 = new AddRandomNumbersThread(numeriCasuali.subList(0, 3));
		AddRandomNumbersThread t1 = new AddRandomNumbersThread(numeriCasuali.subList(3, 6));
		AddRandomNumbersThread t2 = new AddRandomNumbersThread(numeriCasuali.subList(6, 9));

		t0.start();
		try {
			t0.join();
			t1.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		try {
			t1.join();
			t2.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		totale = t0.getTotaleParziale() + t1.getTotaleParziale() + t2.getTotaleParziale();

		logger.info("Totale: " + totale);

	}

	private static List<Integer> generaListaNumeriCasuali() {

		List<Integer> listaNumeri = new ArrayList<Integer>();

		for (int i = 0; i < 9; i++) {

			Random random = new Random();

			int numeroCasuale = random.nextInt(50);

			listaNumeri.add(numeroCasuale);

		}
		return listaNumeri;
	}

}
