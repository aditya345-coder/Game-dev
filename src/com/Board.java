package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.gaming_sprites.Coin;
import com.gaming_sprites.Enemy;
import com.gaming_sprites.Player;

// JPanel is responsible for Painting
public class Board extends JPanel {
    Timer timer;
    BufferedImage backgroundImage;
    Player player;
    Enemy[] enemies = new Enemy[3];
    Coin[] coins = new Coin[6];
    boolean[] coincollide = new boolean[6];
    Scores score;

    public Board() {
        setSize(1500, 920);
        loadBackgroundImage();
        player = new Player();
        score = new Scores();
        loadEnemies();
        loadCoins();
        gameLoop();
        setFocusable(true);
        bindEvents();
    }

    private void loadEnemies() {
        int x = 400;
        int gap = 400;
        int speed = 5;
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(x, speed);
            x = x + gap;
            speed = speed + 5;
        }
    }

    private void loadCoins() {
        int x = 300;
        int gap = 200;
        for (int i = 0; i < coins.length; i++) {
            coins[i] = new Coin(x);
            x = x + gap;
        }
    }

    public void gameOver(Graphics pen) {
        if (player.outOfScreen()) {
            pen.setFont(new Font("times", Font.BOLD, 30));
            pen.setColor(Color.GREEN);
            pen.drawString("Game Win", 1500 / 2, 900 / 2);
            timer.stop();
            return;
        }
        for (Enemy enemy : enemies) {
            if (isCollide(enemy)) {
                pen.setFont(new Font("times", Font.BOLD, 30));
                pen.setColor(Color.RED);
                pen.drawString("GAME OVER", 1500 / 2, 900 / 2);
                timer.stop();
            }
        }
    }

    private boolean isCollide(Enemy enemy) {
        int xDistance = Math.abs(player.x - enemy.x);
        int yDistance = Math.abs(player.y - enemy.y);
        int maxH = Math.max(player.h, enemy.h);
        int maxW = Math.max(player.w, enemy.w);
        return xDistance <= maxW - 80 && yDistance <= maxH - 80;
    }

    public void coinCollect(Graphics pen) {
        for (int i = 0; i < coins.length; i++) {
            if (isCollideWithCoin(coins[i])) {
                coincollide[i] = true;
                score.count = i + 1;
                break;
            }
        }
    }

    private boolean isCollideWithCoin(Coin coin) {
        int xDistance = Math.abs(player.x - coin.x);
        int maxW = Math.max(player.w, coin.w);
        return xDistance <= maxW - 80;
    }

    // Key movements
    private void bindEvents() {
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    player.speed = 0;
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.speed = 10;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.speed = -10;
                }
            }
        });
    }

    private void gameLoop() {
        timer = new Timer(50, (e) -> repaint());
        timer.start();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(Board.class.getResource("Background.jpg"));
        } catch (IOException e) {
            System.out.println("Background Image Not Found....");
            System.exit(1);
            e.printStackTrace();
        }
    }

    private void printEnemies(Graphics pen) {
        for (Enemy enemy : enemies) {
            enemy.draw(pen);
            enemy.move();
        }
    }

    private void printCoins(Graphics pen) {
        for (int i=0;i<coins.length;i++) {
            if (!coincollide[i])
            coins[i].draw(pen);
        }
    }

    // @Override
    public void paintComponent(Graphics pen) {
        // all Printing logic will be there
        super.paintComponent(pen); // clean up
        pen.drawImage(backgroundImage, 0, 0, 1500, 920, null);
        score.draw(pen);
        player.draw(pen);
        player.move();
        printEnemies(pen);
        printCoins(pen);
        coinCollect(pen);
        gameOver(pen);
    }

}
