package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Field;
import hu.asd.towerdefence.Map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class MapView extends Component implements Paintable {

	BufferedImage field;
	BufferedImage road;
	BufferedImage swamp;
	Map map;
	private int tilesize=100;

	public MapView(Map map) {
		this.map = map;
		File fimg = new File("img/field.png");
		File rimg = new File("img/road.png");
		try {
			field = ImageIO.read(fimg);
			road = ImageIO.read(rimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("ASD", 10, 10);
		for (int i=0;i<map.getMap().size();i++){
			int y=i/map.getSize()*tilesize;
			int x=i%map.getSize()*tilesize;
			if (map.getMap().get(i) instanceof Field)
				g.drawImage(field, x, y, null);
			else
				g.drawImage(road, x, y, null);
		}

	}

	
}
