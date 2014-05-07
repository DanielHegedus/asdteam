package hu.asd.towerdefence.view;

import hu.asd.towerdefence.Enemy;
import hu.asd.towerdefence.Game;
import hu.asd.towerdefence.Road;
import hu.asd.towerdefence.Swamp;
import hu.asd.towerdefence.TDActionListener;
import hu.asd.towerdefence.Tower;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicDisplay implements TDActionListener{

	Game game;
	
	public GraphicDisplay() {
		
		
	}
	
	public void setup(){
		JFrame frame = new JFrame("Ket Torony");
		MapView mv  = new MapView(game.getMap());
		frame.add(new MapView(game.getMap()));
		frame.setBounds(100, 100, 500, 500);
		frame.setMinimumSize(new Dimension(500,500));
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		mv.paint(frame.getGraphics());
		//mv.repaint();
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
