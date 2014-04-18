package hu.asd.towerdefence;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) {
		Game game = new Game(new ConsoleDisplay());

		Parser parser = new Parser(game);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		while (true) {
			try {

				parser.parse(reader.readLine());

			} catch (Exception e) {
				Printer.printError(e.getMessage());
				System.out.println("Be sure to call initGame before testing");
			}
		}
	}

}
