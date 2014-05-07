package hu.asd.towerdefence.view;

import hu.asd.towerdefence.DefTower;
import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.Gem;
import hu.asd.towerdefence.Road;
import hu.asd.towerdefence.Swamp;
import hu.asd.towerdefence.TDActionListener;
import hu.asd.towerdefence.Tile;
import hu.asd.towerdefence.Tower;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicDisplay implements TDActionListener{

	Game game;
	private MapView mv;
	private JFrame frame;
	
	public GraphicDisplay() {
		
		
	}
	
	public void setup(){
		frame = new JFrame("Ket Torony");
		mv = new MapView(game.getMap());
		frame.add(mv);
		frame.setBounds(100, 100, 500, 500);
		frame.setMinimumSize(new Dimension(500,500));
		
		//frame.pack();
		frame.setVisible(true);
		//mv.paint(frame.getGraphics());
		mv.repaint();
	}

	@Override
	public void setGame(Game game) {
		this.game=game;
		
	}

	private void repaint(){
		mv.paint(frame.getGraphics());
	}
	
	@Override
	public void onTowerAction(Tower t) {
		repaint();
		
	}

	@Override
	public void onEnemyAction(Enemy e) {
		mv.updateEnemy(e);
		
	}

	@Override
	public void onMapAction(Tile t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMPAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGemAction(Gem gem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameOver(boolean playerHasWon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String message) {
		System.out.println(message);		
	}
}
