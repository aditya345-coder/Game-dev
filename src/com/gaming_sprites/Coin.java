package com.gaming_sprites;

import javax.swing.ImageIcon;

public class Coin extends Sprite {
	public Coin(int x) {
		w = 80;
		h = 80;
		this.x = x;
		y = 450;
		image = new ImageIcon(Player.class.getResource("game-coin.gif"));
	}
}
