package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Dwarf;
import hu.asd.towerdefence.Elf;
import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Hobbit;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class EnemyView extends Component{

	BufferedImage hobbit;
	BufferedImage dwarf;
	BufferedImage elf;
	BufferedImage human;
	
	BufferedImage hobbitDamage;
	BufferedImage dwarfDamage;
	BufferedImage elfDamage;
	BufferedImage humanDamage;
	
	private int y;
	private int x;
	private Enemy enemy;
	private boolean takingDamage;
	
	public EnemyView(Enemy enemy){
		this.setEnemy(enemy);
		File h = new File("img/hobbit.png");
		File d = new File("img/dwarf.png");
		File e = new File("img/elf.png");
		
		File hd = new File("img/hobbit_dmg.png");
		File dd = new File("img/dwarf_dmg.png");
		File ed = new File("img/elf_dmg.png");
		try {
			hobbit = ImageIO.read(h);
			dwarf = ImageIO.read(d);
			elf=ImageIO.read(e);
			
			hobbitDamage = ImageIO.read(hd);
			dwarfDamage = ImageIO.read(dd);
			elfDamage=ImageIO.read(ed);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	@Override
	public void paint(Graphics g){
	
		g.setColor(Color.BLACK);
		g.drawRect(x+10, y-20, 30, 5);
		g.setColor(Color.RED);
		g.fillRect(x+11, y-19, 29, 4);
		g.setColor(Color.GREEN);
		g.fillRect(x+11, y-19, (int) (((enemy.getHP()*1.0)/(enemy.defHP*1.0))*30)-1, 4);
		
		if (takingDamage){
			if (getEnemy() instanceof Hobbit)
				g.drawImage(hobbitDamage,x,y,null);
			else if (getEnemy() instanceof Dwarf)
				g.drawImage(dwarfDamage,x,y,null);
			else if (getEnemy() instanceof Elf)
				g.drawImage(elfDamage,x,y,null);
			takingDamage=false;
		}
		if (getEnemy() instanceof Hobbit)
			g.drawImage(hobbit,x,y,null);
		else if (getEnemy() instanceof Dwarf)
			g.drawImage(dwarf,x,y,null);
		else if (getEnemy() instanceof Elf)
			g.drawImage(elf,x,y,null);
	}
	
	public void setCoords(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void setTakingDamage(boolean takingDamage) {
		this.takingDamage=takingDamage;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	
}
