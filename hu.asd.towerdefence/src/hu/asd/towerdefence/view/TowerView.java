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
public class TowerView extends Component{

	private Tower tower;
	private BufferedImage defImage;
	private BufferedImage spdImage;
	private BufferedImage dmgImage;
	private int x;
	private int y;

	public TowerView(Tower tower){
		//set the tower
		this.tower=tower;
		
		//load the images
		File def=new File("img/def.png");
		File dmg=new File("img/dmg.png");
		File spd=new File("img/spd.png");
		 
		try {
			defImage=ImageIO.read(def);
			dmgImage=ImageIO.read(dmg);
			spdImage=ImageIO.read(spd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void paint(Graphics g){
		if (tower instanceof DefTower)
			g.drawImage(defImage, x, y, null);
		else if (tower instanceof DmgTower)
			g.drawImage(dmgImage, x, y, null);
		else if (tower instanceof SpdTower)
			g.drawImage(spdImage, x, y, null);
	}
	
	public void setCoords(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
