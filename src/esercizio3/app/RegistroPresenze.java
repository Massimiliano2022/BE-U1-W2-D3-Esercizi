package esercizio3.app;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistroPresenze {

	public static Logger logger = LoggerFactory.getLogger(RegistroPresenze.class);

	public static void main(String[] args) {

		File file = new File("registro.txt");

		Map<String, Integer> mapWritingFile = new HashMap<>();
		Map<String, Integer> mapReadingFromFile = new HashMap<>();

		mapWritingFile.put("Mario Rossi", 140);
		mapWritingFile.put("Giuseppe Verdi", 90);
		mapWritingFile.put("Andrea Giordano", 75);
		mapWritingFile.put("Giovanni Storti", 200);
		mapWritingFile.put("Aldo Baglio", 500);
		mapWritingFile.put("Giacomino Poretti", 900);

		scriviFileRegistro(mapWritingFile, file);

		mapReadingFromFile = leggiDatiFile(file);

		stampaMapReadingFromFile(mapReadingFromFile);

		// file.delete();
	}

	private static void scriviFileRegistro(Map<String, Integer> registro, File file) {

		Iterator<Entry<String, Integer>> i = registro.entrySet().iterator();

		while (i.hasNext()) {

			Map.Entry<String, Integer> entry = i.next();

			String dato = entry.getKey() + "@" + entry.getValue() + "#";

			try {
				FileUtils.writeStringToFile(file, dato + System.lineSeparator(), "UTF-8", true);
			} catch (IOException e) {
				logger.error("Errore in scrittura file registro.");
				logger.error(e.getMessage());
			}

		}
	}

	public static Map<String, Integer> leggiDatiFile(File file) {

		Map<String, Integer> registroInScrittura = new HashMap<>();

		try {
			List<String> contents = FileUtils.readLines(file, "UTF-8");
			for (String line : contents) {
				String[] datoPresenza = line.split("@|#");
				String nome = datoPresenza[0];
				int presenze = Integer.parseInt(datoPresenza[1]);
				registroInScrittura.put(nome, presenze);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error("FILE NON TROVATO!");
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
}
