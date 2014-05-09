package hu.asd.towerdefence.view;
/**
 * @author Ruzsics David
 */
import hu.asd.towerdefence.Controller;
import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.Gem;
import hu.asd.towerdefence.Printer;
import hu.asd.towerdefence.TDActionListener;
import hu.asd.towerdefence.Tile;
import hu.asd.towerdefence.Tower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GraphicDisplay implements TDActionListener{

	Game game;
	Controller cntrl;
	private MapView mv;
	private JFrame frame;
	private JPanel panel;
	private ToolbarView tbv;
	private JComponent mPanel;

	
	public GraphicDisplay() {
		cntrl = new Controller();
		frame = new JFrame("Ket Torony");
	//	frame.setBounds(100, 100, 500, 500);
		frame.setMinimumSize(new Dimension(500,660));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setup(){
		frame.setLayout(new BorderLayout());
		mPanel.setVisible(false);
		//creating the mapView

		mv  = new MapView(game.getMap());
		mv.setCntrl(cntrl);
		mv.setPreferredSize(new Dimension(1000,500));
		frame.add(mv, BorderLayout.NORTH);
	
		
		//creating the toolbar
		JPanel toolbarPanel = new JPanel();
		tbv = new ToolbarView(game);
		tbv.setCntrl(cntrl);
		toolbarPanel.setPreferredSize(new Dimension(500,100));
		toolbarPanel.add(tbv);
		frame.add(toolbarPanel, BorderLayout.SOUTH);
		toolbarPanel.setVisible(true);
		frame.add(toolbarPanel);
		frame.pack();	
		frame.validate();
		frame.repaint();
		game.start();

	}
	
	public void menu(){
		//create the panel
		mPanel = new JPanel();
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
				game.load(Game.SAVED_GAME);
				setup();
				
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
		frame.validate();
	}

	@Override
	public void setGame(Game game) {
		this.game=game;
		cntrl.setGame(game);
	}

	private void repaint(){
		mv.repaint();
	}
	
	@Override
	public void onTowerAction(Tower t) {
		repaint();
		
	}

	@Override
	public void onEnemyAction(Enemy e) {
		if (mv!=null)
			mv.updateEnemy(e);		
	}

	@Override
	public void onMapAction(Tile t) {
		repaint();
		
	}

	@Override
	public void onMPAction() {
		tbv.repaint();
	}

	@Override
	public void onGemAction(Gem gem) {
		tbv.repaint();
		
	}

	@Override
	public void onGameOver(boolean playerHasWon) {
		if (playerHasWon)
			JOptionPane.showMessageDialog(null, "You won");	
		else
			JOptionPane.showMessageDialog(null, "You lost");	
		System.exit(0);
	}

	@Override
	public void onError(String message) {
		JOptionPane.showMessageDialog(null, message);	
	}
}
