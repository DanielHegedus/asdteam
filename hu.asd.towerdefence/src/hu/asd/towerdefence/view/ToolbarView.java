package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Controller;
import hu.asd.towerdefence.DmgGem;
import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.Gem;
import hu.asd.towerdefence.MagicPower;
import hu.asd.towerdefence.SpdGem;
import hu.asd.towerdefence.SwpGem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ToolbarView extends JPanel {
	
	private JLabel mpLabel;
	private JLabel gemLabel;
	private Game game;
	private Controller cntrl;
	private GraphicDisplay gd;

	public ToolbarView(final Game game, GraphicDisplay graphicDisplay){
		this.game=game;
		this.gd = graphicDisplay;
		//create and format the panels
		//JPanel base = new JPanel();
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(53, 56, 64));
		this.setPreferredSize(new Dimension(1016,150));
		this.setBackground(new Color(53, 56, 64));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(53, 56, 64));
		JPanel savePanel = new JPanel();
		savePanel.setBackground(new Color(53, 56, 64));
		JLabel gameMenuLabel = new JLabel("GAME MENU:"){
	        {
	            setSize(400, 30);
	            setMaximumSize(getSize());
	        }
	    };
		
		mpLabel = new JLabel("MAGICPOWER:  " + MagicPower.getMP()){
	        {
	            setSize(400, 30);
	            setMaximumSize(getSize());
	        }
	    };
		mpLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mpLabel.setForeground(Color.white);
		mpLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		gemLabel = new JLabel("GEM:  -   "){
	        {
	            setSize(400, 30);
	            setMaximumSize(getSize());
	        }
	    };
		gemLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gemLabel.setForeground(Color.white);
		gemLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		gemLabel.setPreferredSize(new Dimension(400,10));
		gameMenuLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gameMenuLabel.setForeground(Color.white);
		gameMenuLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		//create the buttons 
		JButton buyTower = new JButton("BUY TOWER"){
	        {
	            setSize(215, 30);
	            setMaximumSize(getSize());
	        }
	    };
		JButton buySwamp = new JButton("BUY SWAMP"){
	        {
	            setSize(215, 30);
	            setMaximumSize(getSize());
	        }
	    };
		JButton buyDmgGem = new JButton("BUY DAMAGE GEM"){
	        {
	            setSize(143, 27);
	            setMaximumSize(getSize());
	        }
	    };
		JButton buySpdGem = new JButton("BUY SPEED GEM"){
	        {
	            setSize(144, 27);
	            setMaximumSize(getSize());
	        }
	    };
		JButton buySwpGem = new JButton("BUY SWAMP GEM"){
	        {
	            setSize(143, 27);
	            setMaximumSize(getSize());
	        }
	    };
		JButton saveGame = new JButton("SAVE GAME"){
	        {
	            setSize(215, 30);
	            setMaximumSize(getSize());
	        }
	    };
		JButton quitGame = new JButton("BACK TO MENU"){
	        {
	            setSize(215, 30);
	            setMaximumSize(getSize());
	        }
	    };
		
		//build the toolbar
		//add the items to the top panel
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		topPanel.add(mpLabel);
		topPanel.add(buyTower);
		topPanel.add(buySwamp);
		topPanel.setVisible(true);
		//add the items to the bottom panel
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		bottomPanel.setPreferredSize(new Dimension(700,20));
		bottomPanel.add(gemLabel);
		bottomPanel.add(buyDmgGem);
		bottomPanel.add(buySpdGem);
		bottomPanel.add(buySwpGem);
		bottomPanel.setVisible(true);
		//add the save button and quit button to save panel, and save panel to base
		savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.LINE_AXIS));
		savePanel.add(gameMenuLabel);
		savePanel.add(saveGame);
		savePanel.add(quitGame);
		savePanel.setVisible(true);
		
		//add the panels to base panel
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.CENTER);
		this.add(savePanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setVisible(true);
		
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
		//quit game button action listener
		quitGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//create the menu view TODO
				gd.getMv().setVisible(false);
				gd.getTbv().setVisible(false);
				gd.getFrame().setPreferredSize(new Dimension(500,660));
				
				//refreshing the game
//				gd.game = new Game(gd);
//				gd.game.init();
//				gd.setGame(gd.game);
				gd.menu();
			}
			
		});
	}
	
	public void repaint(){
		super.repaint();
		if (mpLabel!=null)
			mpLabel.setText("MAGICPOWER:  " + MagicPower.getMP());
		if (gemLabel!=null){
			Gem gem=game.getMap().getGem();
			String gemstring="---";
			if (gem!=null){
				gemstring=gem.getClass().getSimpleName().substring(0,3);
			}
			gemLabel.setText("GEM:  "+gemstring);
		}

	}

	public Controller getCntrl() {
		return cntrl;
	}

	public void setCntrl(Controller cntrl) {
		this.cntrl = cntrl;
	}
	
}
