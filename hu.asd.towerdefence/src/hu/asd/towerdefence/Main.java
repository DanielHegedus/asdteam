package hu.asd.towerdefence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String args[]){
		Game game=new Game();
		Map map = game.getMap();
		game.init();
		Printer.print(map);
		Printer.printEnemies(map);
		
		InputStreamReader i = new InputStreamReader(System.in);
		try {
			char[] cbuf=new char[1024];
			while(i.read(cbuf)!=-1){
				map.onTick();
				Printer.print(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Printer.printMP();
		MagicPower.increase(new Hobbit());
		Printer.printMP();
		Printer.printGem(game);
	}
	
}
