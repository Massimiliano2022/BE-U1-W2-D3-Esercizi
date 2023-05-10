package esercizio1.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import esercizio1.app.threads.MyThread;

public class Application {

	public static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		Thread t1 = new Thread(new MyThread("*"));
		Thread t2 = new Thread(new MyThread("#"));

		t1.start();
		t2.start();
	}

}
