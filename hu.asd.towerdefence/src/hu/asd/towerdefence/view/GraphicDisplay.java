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
	private JPanel mPanel;

	
	public GraphicDisplay() {
		cntrl = new Controller();
		setFrame(new JFrame("Ket Torony"));
	//	frame.setBounds(100, 100, 500, 500);
		getFrame().setMinimumSize(new Dimension(500,660));
		getFrame().setVisible(true);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setup(){
		getFrame().setPreferredSize(new Dimension(1016,500));
		getFrame().setLayout(new BorderLayout());
		getmPanel().setVisible(false);
		//creating the mapView
		//game.restart();
		setMv(new MapView(game.getMap()));
		getMv().setCntrl(cntrl);
		getMv().setPreferredSize(new Dimension(1000,500));
		getFrame().add(getMv(), BorderLayout.NORTH); 
	
		
		//creating the toolbar
		//JPanel toolbarPanel = new JPanel();
		setTbv(new ToolbarView(game, this));
		tbv.setVisible(true);
		getTbv().setCntrl(cntrl);
		//toolbarPanel.setPreferredSize(new Dimension(500,100));
		//toolbarPanel.add(getTbv());
		tbv.setPreferredSize(new Dimension(500,100));
	//	getFrame().add(toolbarPanel, BorderLayout.SOUTH);
		getFrame().add(tbv, BorderLayout.SOUTH);
		//toolbarPanel.setVisible(true);
		//getFrame().add(toolbarPanel);
		getFrame().add(tbv);
		getFrame().pack();	
		getFrame().validate();
		getFrame().repaint();
		game.start();

	}
	
	public void menu(){
		//create the panel
		getFrame().setPreferredSize(new Dimension(500,660));
		setmPanel(new JPanel());
		getFrame().add(getmPanel());
		getmPanel().setBorder(new EmptyBorder(20, 20, 20, 20));
		getmPanel().setLayout(new GridLayout(4,1));
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
				game.init();
				setup();
				getmPanel().setVisible(false);
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
		getmPanel().add(title);
		getmPanel().add(newGame);
		getmPanel().add(loadGame);
		getmPanel().add(exitGame);
		getmPanel().setBackground(new Color(53, 56, 64));
		getmPanel().setVisible(true);
		getFrame().validate();
	}

	@Override
	public void setGame(Game game) {
		this.game=game;
		cntrl.setGame(game);
	}

	private void repaint(){
		getMv().repaint();
	}
	
	@Override
	public void onTowerAction(Tower t) {
		repaint();
		
	}

	@Override
	public void onEnemyAction(Enemy e) {
		if (getMv()!=null)
			getMv().updateEnemy(e);		
	}

	@Override
	public void onMapAction(Tile t) {
		repaint();
		
	}

	@Override
	public void onMPAction() {
		getTbv().repaint();
	}

	@Override
	public void onGemAction(Gem gem) {
		getTbv().repaint();
		
	}

	@Override
	public void onGameOver(boolean playerHasWon) {
		getMv().gameOver(playerHasWon);
		getMv().repaint();
//		if (playerHasWon)
//			JOptionPane.showMessageDialog(null, "You won");	
//		else
//			JOptionPane.showMessageDialog(null, "You lost");	
//		System.exit(0);
	}

	@Override
	public void onError(String message) {
		JOptionPane.showMessageDialog(null, message);	
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public MapView getMv() {
		return mv;
	}

	public void setMv(MapView mv) {
		this.mv = mv;
	}

	public ToolbarView getTbv() {
		return tbv;
	}

	public void setTbv(ToolbarView tbv) {
		this.tbv = tbv;
	}

	public JPanel getmPanel() {
		return mPanel;
	}

	public void setmPanel(JPanel mPanel) {
		this.mPanel = mPanel;
	}
}
