package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Controller;
import hu.asd.towerdefence.DmgGem;
import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.Gem;
import hu.asd.towerdefence.MagicPower;
import hu.asd.towerdefence.SpdGem;
import hu.asd.towerdefence.SwpGem;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ToolbarView extends JPanel {
	

	private JLabel mpLabel;
	private JLabel gemLabel;
	private Game game;
	private Controller cntrl;

	public ToolbarView(final Game game){
		this.game=game;
		//create the panels
		JPanel base = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
		mpLabel = new JLabel("MAGICPOWER: " + MagicPower.getMP());
		gemLabel = new JLabel("GEM: -");
		
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
		
		//button listeners
		//buy tower action listner
		buyTower.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getCntrl().setNewTower(true);				
			}});
		//buy swamp action listener
		buySwamp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getCntrl().setNewSwamp(true);
				
			}
			
		});
		//buy dmg gem action listener
		buyDmgGem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getCntrl().buyGem(new DmgGem());
				
			}
			
		});
		//buy spd gem action listener
		buySpdGem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getCntrl().buyGem(new SpdGem());
				
			}});
		//buy swp gem action listener
		buySwpGem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getCntrl().buyGem(new SwpGem());
				
			}});
		//save game button action listener
		saveGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					game.save(Game.SAVED_GAME);
			}});
	}
	
	public void repaint(){
		super.repaint();
		if (mpLabel!=null)
			mpLabel.setText("MAGICPOWER: " + MagicPower.getMP());
		if (gemLabel!=null){
			Gem gem=game.getMap().getGem();
			String gemstring="-";
			if (gem!=null){
				gemstring=gem.getClass().getSimpleName().substring(0,2);
			}
			gemLabel.setText("GEM: "+gemstring);
		}

	}

	public Controller getCntrl() {
		return cntrl;
	}

	public void setCntrl(Controller cntrl) {
		this.cntrl = cntrl;
	}
	
}
