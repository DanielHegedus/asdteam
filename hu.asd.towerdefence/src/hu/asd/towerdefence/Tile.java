package hu.asd.towerdefence;
import java.util.ArrayList;
import java.util.List;

//terkepunk alapegysege
public abstract class Tile {
	List<Tile> neighbours; // a szomszédokat tartalmazó lista
	TDActionListener listener;
	
	public Tile(){
		neighbours=new ArrayList<Tile>();
	}
	
	//szomszedok beallitasa
	public void setNeighbour(Tile tile) {
		neighbours.add(tile);
	}
	
	//visszaadja a szomszedokat
	public List<Tile> getNeighbours(){		
		return neighbours;
	}
	
	public void setListener(TDActionListener listener) {
		this.listener=listener;
	}
	

}
