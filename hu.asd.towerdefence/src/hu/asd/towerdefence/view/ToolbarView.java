package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.MagicPower;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ToolbarView extends JPanel {
	

	public ToolbarView(Game game){
		
		//create the panels
		JPanel base = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
		//create the labels
		JLabel mpLabel = new JLabel("MAGICPOWER: " + MagicPower.getMP());
		JLabel gemLabel = new JLabel("GEM: -");
		
		//create the buttons
		JButton buyTower = new JButton("BUY TOWER");
		JButton buySwamp = new JButton("BUY SWAMP");
		JButton buyDmgGem = new JButton("BUY DAMAGE GEM");
		JButton buySpdGem = new JButton("BUY SPEED GEM");
		JButton buySwpGem = new JButton("BUY SWAMP GEM");
		JButton saveGame = new JButton("SAVE GAME");
		
		//build the toolbar
		//add the items to the top panel
		topPanel.add(mpLabel);
		topPanel.add(buyTower);
		topPanel.add(buySwamp);
		topPanel.setVisible(true);
		//add the items to the bottom panel
		bottomPanel.add(gemLabel);
		bottomPanel.add(buyDmgGem);
		bottomPanel.add(buySpdGem);
		bottomPanel.add(buySwpGem);
		bottomPanel.setMaximumSize(new Dimension(500,500));
		bottomPanel.setVisible(true);
		//add the save button to save panel, and save panel to base
		savePanel.add(saveGame);
		savePanel.setVisible(true);
		//add the panels to base panel
		base.setLayout(new BorderLayout());
		base.add(topPanel, BorderLayout.EAST);
		base.add(bottomPanel, BorderLayout.EAST);
		base.add(savePanel, BorderLayout.WEST);
		base.setLayout(new GridLayout(3,1));
		base.setVisible(true);
		this.add(base);
		this.setMaximumSize(new Dimension(500,150));
		this.setVisible(true);
		System.out.println("asd");
	}
	
}
