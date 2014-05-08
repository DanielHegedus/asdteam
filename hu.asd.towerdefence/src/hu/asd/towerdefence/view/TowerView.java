package hu.asd.towerdefence.view;

/**
 * TowerView.java
 * 
 * Class for displaying towers
 * 
 * @author Daniel Hegedus
 */

import hu.asd.towerdefence.DefTower;
import hu.asd.towerdefence.DmgTower;
import hu.asd.towerdefence.SpdTower;
import hu.asd.towerdefence.Tower;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class TowerView extends Component {

	private Tower tower;
	private BufferedImage defImage;
	private BufferedImage spdImage;
	private BufferedImage dmgImage;

	private BufferedImage defFog;
	private BufferedImage spdFog;
	private BufferedImage dmgFog;

	private int x;
	private int y;

	public TowerView(Tower tower) {
		// set the tower
		this.setTower(tower);

		// load the images
		File def = new File("img/def.png");
		File dmg = new File("img/dmg.png");
		File spd = new File("img/spd.png");

		File fdef = new File("img/def_fog.png");
		File fdmg = new File("img/dmg_fog.png");
		File fspd = new File("img/spd_fog.png");

		try {
			defImage = ImageIO.read(def);
			dmgImage = ImageIO.read(dmg);
			spdImage = ImageIO.read(spd);

			defFog = ImageIO.read(fdef);
			dmgFog = ImageIO.read(fdmg);
			spdFog = ImageIO.read(fspd);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void paint(Graphics g) {
		if (tower.isInFog()) {
			if (getTower() instanceof DefTower)
				g.drawImage(defFog, x, y, null);
			else if (getTower() instanceof DmgTower)
				g.drawImage(dmgFog, x, y, null);
			else if (getTower() instanceof SpdTower)
				g.drawImage(spdFog, x, y, null);
		} else {
			if (getTower() instanceof DefTower)
				g.drawImage(defImage, x, y, null);
			else if (getTower() instanceof DmgTower)
				g.drawImage(dmgImage, x, y, null);
			else if (getTower() instanceof SpdTower)
				g.drawImage(spdImage, x, y, null);
		}

	}

	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}
}
