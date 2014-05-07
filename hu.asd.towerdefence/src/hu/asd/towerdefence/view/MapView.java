package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Field;
import hu.asd.towerdefence.Map;
import hu.asd.towerdefence.Mordor;
import hu.asd.towerdefence.Road;
import hu.asd.towerdefence.SuperSwamp;
import hu.asd.towerdefence.Swamp;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class MapView extends Component implements Paintable {

	BufferedImage field;
	BufferedImage road;
	BufferedImage swamp;
	BufferedImage superSwamp;
	BufferedImage mordor;
	
	private List<EnemyView> enemies;
	private List<TowerView> towers;
	
	Map map;
	private int tilesize=100;
	

	public MapView(Map map) {
		towers=new ArrayList<TowerView>();
		enemies=new ArrayList<EnemyView>();
		
		this.map = map;
		File fimg = new File("img/field.png");
		File rimg = new File("img/road.png");
		File simg = new File("img/swamp.png");
		File ssimg = new File("img/superswamp.png");
		File mimg = new File("img/mordor.png");
		try {
			field = ImageIO.read(fimg);
			road = ImageIO.read(rimg);
			swamp=ImageIO.read(simg);
			superSwamp=ImageIO.read(ssimg);
			mordor=ImageIO.read(mimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawString("ASD", 10, 10);
		for (int i=0;i<map.getMap().size();i++){
			int y=i/map.getSize()*tilesize;
			int x=i%map.getSize()*tilesize;
			if (map.getMap().get(i) instanceof Field){
				g.drawImage(field, x, y, null);
				Field field = (Field) map.getMap().get(i);
				if (field.getTower()!=null){
					TowerView tv=new TowerView(field.getTower());
					tv.setCoords(x, y);
					tv.paint(g);
					
				}
			}
			else{
				g.drawImage(road, x, y, null);
				Road r = (Road) map.getMap().get(i);
				if (r instanceof SuperSwamp)
					g.drawImage(superSwamp, x, y, null);
				else if (r instanceof Swamp)
					g.drawImage(swamp, x, y, null);
				else if ( r instanceof Mordor){
					g.drawImage(mordor, x, y, null);
				}
					
				
				if (r.hasEnemy() != null){
					for (Enemy e : r.getEnemies()){
						EnemyView ev = getEnemyView(e);
						ev.setCoords(x, y);
						ev.paint(getGraphics());
						
					}
				}
			}
		}

	}
	
	public void updateEnemy(Enemy e) {
		EnemyView ev=getEnemyView(e);
		ev.setTakingDamage(true);
		ev.paint(getGraphics());
		
	}
	
	private EnemyView getEnemyView(Enemy e){
		for (EnemyView ev:enemies){
			if (ev.getEnemy().equals(e)){
				return ev;
			}
		}
		EnemyView ev=new EnemyView(e);
		enemies.add(ev);
		return ev;
		
	}

	
}
