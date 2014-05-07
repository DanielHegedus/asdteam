package hu.asd.towerdefence.view;
/**
 * @author Ruzsics David
 */
import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.Road;
import hu.asd.towerdefence.Swamp;
import hu.asd.towerdefence.TDActionListener;
import hu.asd.towerdefence.Tower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GraphicDisplay implements TDActionListener{

	Game game;
	private JFrame frame;
	
	public GraphicDisplay() {
		frame = new JFrame("Ket Torony");
	//	frame.setBounds(100, 100, 500, 500);
		frame.setMinimumSize(new Dimension(500,660));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setup(){
		frame.setLayout(new BorderLayout());
		game.init();
		
		//creating the mapView
		MapView mv  = new MapView(game.getMap());
		mv.setPreferredSize(new Dimension(500,500));
		frame.add(mv, BorderLayout.NORTH);
		mv.paint(frame.getGraphics());
		//mv.repaint();
		
		//creating the toolbar
		JPanel toolbarPanel = new JPanel();
		ToolbarView tbv = new ToolbarView(game);
		toolbarPanel.setPreferredSize(new Dimension(500,100));
		toolbarPanel.add(tbv);
		frame.add(toolbarPanel, BorderLayout.SOUTH);
		toolbarPanel.setVisible(true);
		frame.add(toolbarPanel);
		//frame.pack();
		
	}
	
	public void menu(){
		//create the panel
		final JPanel mPanel = new JPanel();
		frame.add(mPanel);
		mPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mPanel.setLayout(new GridLayout(4,1));
		//create and format the title
		JLabel title = new JLabel("ASD TEAM - TOWER DEFENSE");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.red);
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		//create and format buttons
		JButton newGame = new JButton("NEW GAME");
		newGame.setBorder(BorderFactory.createLineBorder(new Color(53, 56, 64), 20));
		//onclick action listener to newGame
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setup();
				mPanel.setVisible(false);
			}});
		
		JButton loadGame = new JButton("LOAD GAME");
		loadGame.setBorder(BorderFactory.createLineBorder(new Color(53, 56, 64), 20));
		//onclick action listener to load game
		loadGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.load("game.asd");
			}});
		JButton exitGame = new JButton("EXIT");
		exitGame.setBorder(BorderFactory.createLineBorder(new Color(53, 56, 64), 20));
		//onclick action listener to exit game
		exitGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}});
		//add components to the panel
		mPanel.add(title);
		mPanel.add(newGame);
		mPanel.add(loadGame);
		mPanel.add(exitGame);
		mPanel.setBackground(new Color(53, 56, 64));
		mPanel.setVisible(true);
	}

	@Override
	public void setGame(Game game) {
		this.game=game;
		
	}

	@Override
	public void onEnemyDamage(Enemy e, int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnemyMovement(Enemy e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnemyBlock(Enemy e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTowerShooting(Tower t, Enemy e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTowerNotShooting(Tower t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTowerUpgrade(Tower t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTowerFog(Tower t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSwampAdded(Swamp s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notEnoughMP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wrongTileSelected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMPGain() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnemyCreated(Enemy newEnemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNoEnemyInSight(Tower tower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnteredRoad(Enemy enemy, Road road) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeftRoad(Enemy enemy, Road road) {
		// TODO Auto-generated method stub
		
	}
}
