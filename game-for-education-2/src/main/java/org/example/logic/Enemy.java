package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Entity {
    private int damage;

    public Enemy(int x, int y, String url, int damage) {
        super(x, y, url);
        this.damage = damage;
    }

    public boolean isEnemyCollided(ArrayList<Wall> walls) {
        for (Wall wall: walls){
            if (this.isEnemyCollided(wall.getRectangle())){
                return true;
            }
        }
        return false;
    }

}
