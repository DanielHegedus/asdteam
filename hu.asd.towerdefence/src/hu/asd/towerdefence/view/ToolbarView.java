package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.MagicPower;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ToolbarView extends JLabel {
	
	JLabel jl;

	public ToolbarView(Game game){
		
		//create the panels
		JPanel base = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
		//create the labels
		JLabel mpLabel = new JLabel("MAGICPOWER: " + MagicPower.getMP());
		JLabel gemLabel = new JLabel("GAME: ");
		
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
		bottomPanel.setVisible(true);
		//add the save button to save panel, and save panel to base
		savePanel.add(saveGame);
		savePanel.setVisible(true);
		base.add(savePanel);
		//add the top and bottom panel to base panel
		base.add(topPanel);
		base.add(bottomPanel);
		base.setLayout(new GridLayout(2,2));
		base.setVisible(true);
		this.add(base);
		System.out.println("asd");
	}
	
	public void asdtest(){
		jl = new JLabel("asdfg");
		jl.setVisible(true);
	}
}
