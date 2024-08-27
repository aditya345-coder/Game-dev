package com.gaming_sprites;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

import com.Scores;

public class Player extends Sprite {
    
    public Player(){
        w = 200;
        h = 200;
        x = 50;
        y = 450;
        image = new ImageIcon(Player.class.getResource("player.gif"));
    }
    public void move() {
        x = x + speed;
    }
    public boolean outOfScreen(){
        return x>1490; 
    }
    public boolean hasCollected(Coin coin, Scores score)
		{
			Rectangle coinRect = new Rectangle(coin.x, coin.y, coin.w + 1, coin.h + 1);
			Rectangle playerRect = new Rectangle(x, y, w + 1, h + 1);
			
			if(coinRect.intersects(playerRect))
			{
				coin.x = -1000;
				coin = null;
				// board.score++;
                score.count++;
				return true;
			}
		
//			if(bombRect.intersects(playerRect)) {
//				bombs.xPos = -1000;
//				bombs = null;
//				BackGround.score--;
//				return true;
//			}
        
			return false;
		}
}
