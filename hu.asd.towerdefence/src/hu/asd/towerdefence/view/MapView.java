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
import java.awt.Component;
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

	BufferedImage field;
	BufferedImage road;
	BufferedImage swamp;
	BufferedImage superSwamp;
	BufferedImage mordor;

	private List<EnemyView> enemies;
	private List<TowerView> towers;

	Map map;
	private int tilesize = 50;
	private Graphics graphics;
	private Controller cntrl;
	private boolean playerWon;
	private boolean gameOver;

	public MapView(Map map) {
		towers = new ArrayList<TowerView>();
		enemies = new ArrayList<EnemyView>();

		this.map = map;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addMouseListener(new TileMouseListener());
	}

	public void paintComponent(Graphics g) {
		graphics = g;
		super.paintComponent(g);
		paint(g);
	}

	@Override
	public void paint(Graphics g) {

		// super.paint(g);
		for (int i = 0; i < map.getMap().size(); i++) {
			int y = i / map.getSize() * tilesize;
			int x = i % map.getSize() * tilesize;
			if (map.getMap().get(i) instanceof Field) {
				g.drawImage(field, x, y, null);
				Field field = (Field) map.getMap().get(i);
				if (field.getTower() != null) {
					TowerView tv = getTowerView(field.getTower());
					tv.setCoords(x, y);
					tv.paint(g);

				}
			} else {
				g.drawImage(road, x, y, null);
				Road r = (Road) map.getMap().get(i);
				if (r instanceof SuperSwamp)
					g.drawImage(superSwamp, x, y, null);
				else if (r instanceof Swamp)
					g.drawImage(swamp, x, y, null);
				else if (r instanceof Mordor) {
					g.drawImage(mordor, x, y, null);
				}

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

	public void updateEnemy(Enemy e) {
		EnemyView ev = getEnemyView(e);
		ev.setTakingDamage(true);
		// ev.paint(getGraphics());

	}

	private EnemyView getEnemyView(Enemy e) {
		for (EnemyView ev : enemies) {
			if (ev.getEnemy().equals(e)) {
				return ev;
			}
		}
		EnemyView ev = new EnemyView(e);
		enemies.add(ev);
		return ev;

	}

	private TowerView getTowerView(Tower t) {
		for (TowerView tv : towers) {
			if (tv.getTower().equals(t)) {
				return tv;
			}
		}
		TowerView tv = new TowerView(t);
		towers.add(tv);
		return tv;

	}

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
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			//megkeressuk a tile-t a kattintas koordinatai alapjan
			int y = e.getX() / tilesize;
			int x = e.getY() / tilesize;
			int tile = x * map.getSize() + y;
			getCntrl().tileAction(map.getMap().get(tile));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
