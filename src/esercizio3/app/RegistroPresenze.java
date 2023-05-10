package esercizio3.app;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistroPresenze {

	public static Logger logger = LoggerFactory.getLogger(RegistroPresenze.class);

	public static void main(String[] args) {

		File file = new File("registro.txt");

		Map<String, Integer> mapWritingFile = new HashMap<>();
		Map<String, Integer> mapReadingFromFile = new HashMap<>();

		Scanner scanner = new Scanner(System.in);

		int input;
		boolean runApplication = true;

		while (runApplication) {
			System.out.println("****REGISTRO PRESENZE****");
			System.out.println("Quale operazione intendi eseguire?");
			System.out.println("1 - per aggiungere un nuovo dato;");
			System.out.println("2 - visualizzare i dati presenti nel registro;");
			System.out.println("3 - cancellare il file registro presenze");
			System.out.println("0 - per terminare");

			input = scanner.nextInt();

			switch (input) {
			case 1:
				inserisciDatiRegistro(mapWritingFile, scanner);
				scriviFileRegistro(mapWritingFile, file);
				break;
			case 2:
				mapReadingFromFile = leggiDatiFile(file);
				stampaMapReadingFromFile(mapReadingFromFile);
				break;
			case 3:
				// mapReadingFromFile = leggiDatiFile(file);
				eliminaDatiMapReadingFromFile(mapWritingFile, mapReadingFromFile, file);
				break;
			case 0:
				runApplication = false;
				logger.info("Termino l'applicazione.");
				break;
			default:
				logger.info("Inserisci un input valido!");
				break;
			}
		}

		scanner.close();

	}

	private static void inserisciDatiRegistro(Map<String, Integer> mapWritingFile, Scanner scanner) {

		scanner.nextLine();

		String nomeDipendente;
		int orePresenza = 0;

		do {
			System.out.println("Inserisci nome e cognome dipendente:");
			nomeDipendente = scanner.nextLine();
			if (nomeDipendente.equals("")) {
				logger.error("Inserisci il nome del DIPENDENTE!");
			}
		} while (nomeDipendente.equals(""));

		do {
			System.out.println("Inserisci le ore di presenza per il dipendente:");
			orePresenza = scanner.nextInt();
			if (orePresenza == 0 || orePresenza < 0) {
				logger.error("Non puoi inserire VALORI NEGATIVI per le ore di presenza!");
			}
		} while (orePresenza == 0 || orePresenza < 0);

		mapWritingFile.put(nomeDipendente, orePresenza);

	}

	private static void scriviFileRegistro(Map<String, Integer> registro, File file) {

		Iterator<Entry<String, Integer>> i = registro.entrySet().iterator();

		while (i.hasNext()) {

			Map.Entry<String, Integer> entry = i.next();

			String presenza = entry.getKey() + "@" + entry.getValue() + "#";

			try {
				FileUtils.writeStringToFile(file, presenza + System.lineSeparator(), "UTF-8", true);
			} catch (IOException e) {
				// e.printStackTrace();
				logger.error(e.getMessage());
				logger.error("Errore in scrittura file registro.");
			}

		}
	}

	public static Map<String, Integer> leggiDatiFile(File file) {

		Map<String, Integer> registroInScrittura = new HashMap<>();

		if (file.exists()) {

			try {
				List<String> contents = FileUtils.readLines(file, "UTF-8");
				for (String line : contents) {
					String[] datoPresenza = line.split("@|#");
					String nome = datoPresenza[0];
					int presenze = Integer.parseInt(datoPresenza[1]);
					registroInScrittura.put(nome, presenze);
				}
			} catch (IOException e) {
				// e.printStackTrace();
				logger.error(e.getMessage());
				logger.error("FILE NON TROVATO!");
			}
		} else {
			logger.info("Il file deve essere generato.");
		}

		return registroInScrittura;
	}

	private static void stampaMapReadingFromFile(Map<String, Integer> mapReadingFromFile) {

		Iterator<Entry<String, Integer>> i = mapReadingFromFile.entrySet().iterator();

		while (i.hasNext()) {

			Map.Entry<String, Integer> entry = i.next();

			System.out.println("Nome: " + entry.getKey() + " " + "Presenze: " + entry.getValue());

		}
	}

	private static void eliminaDatiMapReadingFromFile(Map<String, Integer> mapWritingFile,
			Map<String, Integer> mapReadingFromFile, File file) {

		mapWritingFile.clear();
		mapReadingFromFile.clear();

		if (file.exists()) {
			file.delete();
		} else {
			logger.info("File non presente. Genera un nuovo file registro presenze.");
		}

	}

}
