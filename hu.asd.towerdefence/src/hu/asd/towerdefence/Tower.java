package hu.asd.towerdefence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Tower {

	private List<Road> neighbours; // lista a torony koruli utakrol
	private int speed; // lovesi sebesseg
	private int power; // lovesi ero
	private int timeleft; // ennyi ido mulva lo
	private int cost; // torony ara
	private Field field; // field amin a torony van

	// sebzi a parameterkent megadott ellenseget
	public void doDamage(Enemy enemy) {
		System.out.println("doDamage(" + enemy.getClass().getName() + ")");
		System.out.print(" --> enemy.");
		enemy.lowerHP(5);
	}

	// visszaadja a torony arat
	public int getCost() {
		System.out.println("getCost()");
		return cost;
	}

	// beallitja a torony arat
	public void setCost(int i) {

	}

	// beallitja a fieldet amin a torony lesz
	public void setField(Field field) {
		System.out.println("setField(field)");
		System.out.print("  --> field.");
		field.getNeighbours(); // viszaadja a szomszedokat TODO

		System.out.print("  --> field.");
		field.setTower(this); // megadja a fieldnek, hogy ez a torony van rajta
		System.out.println(" <--");
	}

	public void onTick() {
		System.out.println("onTick()");
		System.out.println(" [?] Lõhet a torony? y/n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (s.equals("y")) {
			// ha lõhet a torony, akkor megkérdezi szomszédos utakat, hogy van-e
			// rajtuk ellenség
			Road aNeighbourRode = new Road();
			System.out.print("  --> aNeighbourRoad.");
			aNeighbourRode.hasEnemy();
			System.out.println(" [?] Van ellenség hatótávon belül? y/n");
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (s.equals("y")) {
				Hobbit anEnemy = new Hobbit();
				System.out.print("  --> anEnemy.");
				anEnemy.shoot(this);
			}
		}
	}
}
