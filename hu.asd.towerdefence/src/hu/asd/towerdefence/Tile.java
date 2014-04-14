package hu.asd.towerdefence;
import java.util.ArrayList;
import java.util.List;

//terkepunk alapegysege
public abstract class Tile {
	List<Tile> neighbours; // a szomszédokat tartalmazó lista
	
	//szomszedok beallitasa
	public void setNeighbour(Tile tile) {
		System.out.println("setNeighbour(" + tile.getClass().getName() + ")");
	}
	
	//visszaadja a szomszedokat TODO: visszateresi ertek
	public List<Tile> getNeighbours() {
		System.out.println("getNeighbours()");
		
		return neighbours;
	}
	

}
