package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Scores {
    public int count;
    void draw(Graphics pen){
        pen.setFont(new Font("times", Font.BOLD, 30));
        pen.setColor(Color.RED);
        pen.drawString("Score: "+count, 400, 50);
    }
}
