package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Controller;
import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Field;
import hu.asd.towerdefence.Map;
import hu.asd.towerdefence.Mordor;
import hu.asd.towerdefence.Road;
import hu.asd.towerdefence.SuperSwamp;
import hu.asd.towerdefence.Swamp;
import hu.asd.towerdefence.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapView extends JPanel {

	//images for the tiles
	BufferedImage field;
	BufferedImage road;
	BufferedImage swamp;
	BufferedImage superSwamp;
	BufferedImage mordor;

	//list of enemies and towers
	private List<EnemyView> enemies;
	private List<TowerView> towers;

	Map map;
	private int tilesize = 50;
	private Controller cntrl;
	private boolean playerWon;
	private boolean gameOver;

	/**
	 * Constructor - creates a new MapView and loads the necessary images
	 * @param map the map to be shown
	 */
	public MapView(Map map) {
		towers = new ArrayList<TowerView>();
		enemies = new ArrayList<EnemyView>();
		this.map = map;
		
		//load the images
		File fimg = new File("img/field.png");
		File rimg = new File("img/road.png");
		File simg = new File("img/swamp.png");
		File ssimg = new File("img/superswamp.png");
		File mimg = new File("img/mordor.png");
		try {
			field = ImageIO.read(fimg);
			road = ImageIO.read(rimg);
			swamp = ImageIO.read(simg);
			superSwamp = ImageIO.read(ssimg);
			mordor = ImageIO.read(mimg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//set a mouselistener to process clicks
		this.addMouseListener(new TileMouseListener());
	}

	
	//overrides how the panel is drawn
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint(g);
	}

	@Override
	public void paint(Graphics g) {

		//iterate over the map and draw each tile
		for (int i = 0; i < map.getMap().size(); i++) {
			//calculate the position based on the tile and map sizes
			int y = i / map.getSize() * tilesize;
			int x = i % map.getSize() * tilesize;
			
			//draw fields and towers
			if (map.getMap().get(i) instanceof Field) {
				g.drawImage(field, x, y, null);
				Field field = (Field) map.getMap().get(i);
				if (field.getTower() != null) {
					TowerView tv = getTowerView(field.getTower());
					tv.setCoords(x, y);
					tv.paint(g);

				}
			} else {
				//if not field draw the road or swamp
				g.drawImage(road, x, y, null);
				Road r = (Road) map.getMap().get(i);
				if (r instanceof SuperSwamp)
					g.drawImage(superSwamp, x, y, null);
				else if (r instanceof Swamp)
					g.drawImage(swamp, x, y, null);
				else if (r instanceof Mordor) {
					g.drawImage(mordor, x, y, null);
				}

				//draw the enemies on that piece of road
				if (r.hasEnemy() != null) {
					int offset = 20;
					for (Enemy e : r.getEnemies()) {
						EnemyView ev = getEnemyView(e);
						ev.setCoords(x + offset, y + offset);
						offset += 5;
						if (offset > 20)
							offset = 0;
						ev.paint(g);

					}
				}
			}
		}
		
		//if the game is over fill the screen and write out the result
		if (gameOver){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.WHITE);
			if (playerWon)
				g.drawString("YOU WON", this.getWidth()/2, this.getHeight()/2);
			else
				g.drawString("YOU LOST", this.getWidth()/2, this.getHeight()/2);
		}

	}

	//sets that the enemy is taking damage
	public void updateEnemy(Enemy e) {
		EnemyView ev = getEnemyView(e);
		ev.setTakingDamage(true);
	}

	//returns the enemyview for the given enemy
	private EnemyView getEnemyView(Enemy e) {
		for (EnemyView ev : enemies) {
			if (ev.getEnemy().equals(e)) {
				return ev;
			}
		}
		//if it's not already in the list create a new and add it
		EnemyView ev = new EnemyView(e);
		enemies.add(ev);
		return ev;

	}

	//returns the towerview for the given enemy
	private TowerView getTowerView(Tower t) {
		for (TowerView tv : towers) {
			if (tv.getTower().equals(t)) {
				return tv;
			}
		}
		//if it's not already in the list create a new and add it
		TowerView tv = new TowerView(t);
		towers.add(tv);
		return tv;

	}

	//setters and getters
	public Controller getCntrl() {
		return cntrl;
	}

	public void setCntrl(Controller cntrl) {
		this.cntrl = cntrl;
	}
	
	public void gameOver(boolean playerWon){
		this.gameOver = true;
		this.playerWon = playerWon;
	}

	private class TileMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			//get the tile based on the click coordinates
			int y = e.getX() / tilesize;
			int x = e.getY() / tilesize;
			int tile = x * map.getSize() + y;
			getCntrl().tileAction(map.getMap().get(tile));
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

	}

}
