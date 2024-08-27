package com;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    // Every Class has a Default Consturctor
    public GameFrame() {
        Board board = new Board();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game Dev in Java"); // `this` keyword is by default added by java
        setSize(1500, 920);
        setResizable(false);
        setLocationRelativeTo(null);
        add(board);
        setVisible(true); // to make fram visible
    }

    public static void main(String[] args) {
        new GameFrame();

    }
}