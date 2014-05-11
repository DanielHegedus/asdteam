package hu.asd.towerdefence.view;

/**
 * EnemyView.java
 * Class for displaying the different enemies
 */
import hu.asd.towerdefence.Dwarf;
import hu.asd.towerdefence.Elf;
import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Hobbit;
import hu.asd.towerdefence.Human;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class EnemyView extends Component {

	// basic images for each type
	BufferedImage hobbit;
	BufferedImage dwarf;
	BufferedImage elf;
	BufferedImage human;

	// images when taking damage
	BufferedImage hobbitDamage;
	BufferedImage dwarfDamage;
	BufferedImage elfDamage;
	BufferedImage humanDamage;

	// the coordinates to draw at
	private int y;
	private int x;

	private Enemy enemy;
	private boolean takingDamage;

	public EnemyView(Enemy enemy) {
		// load the images into files
		this.setEnemy(enemy);
		File h = new File("img/hobbit.png");
		File d = new File("img/dwarf.png");
		File e = new File("img/elf.png");
		File u = new File("img/human.png");

		File hd = new File("img/hobbit_dmg.png");
		File dd = new File("img/dwarf_dmg.png");
		File ed = new File("img/elf_dmg.png");
		File ud = new File("img/human_dmg.png");
		try {
			// read the images from the files
			hobbit = ImageIO.read(h);
			dwarf = ImageIO.read(d);
			elf = ImageIO.read(e);
			human = ImageIO.read(u);

			hobbitDamage = ImageIO.read(hd);
			dwarfDamage = ImageIO.read(dd);
			elfDamage = ImageIO.read(ed);
			humanDamage = ImageIO.read(ud);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void paint(Graphics g) {

		// the healthbar
		g.setColor(Color.BLACK);
		g.drawRect(x + 3, y - 10, 20, 5);
		g.setColor(Color.RED);
		g.fillRect(x + 4, y - 9, 19, 4);
		g.setColor(Color.GREEN);
		g.fillRect(x + 4, y - 9,
				(int) (((enemy.getHP() * 1.0) / (enemy.defHP * 1.0)) * 20) - 1,
				4);

		// if the enemy is taking damage show that image
		if (takingDamage) {
			if (getEnemy() instanceof Hobbit)
				g.drawImage(hobbitDamage, x, y, null);
			else if (getEnemy() instanceof Dwarf)
				g.drawImage(dwarfDamage, x, y, null);
			else if (getEnemy() instanceof Elf)
				g.drawImage(elfDamage, x, y, null);
			else if (getEnemy() instanceof Human)
				g.drawImage(humanDamage, x, y, null);
			takingDamage = false;
		} else {
			//else show the normal
			if (getEnemy() instanceof Hobbit)
				g.drawImage(hobbit, x, y, null);
			else if (getEnemy() instanceof Dwarf)
				g.drawImage(dwarf, x, y, null);
			else if (getEnemy() instanceof Elf)
				g.drawImage(elf, x, y, null);
			else if (getEnemy() instanceof Human)
				g.drawImage(human, x, y, null);
		}
	}

	//method for setting the coordinates
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//tell whether the enemy is taking damage or not
	public void setTakingDamage(boolean takingDamage) {
		this.takingDamage = takingDamage;
	}

	
	//setter and getter for the enemy reference
	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

}
