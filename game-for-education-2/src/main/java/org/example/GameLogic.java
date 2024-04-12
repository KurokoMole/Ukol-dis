package org.example;

import org.example.logic.*;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    private Ball ball;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;
    private final int ENEMY_STEPS = 5;
    private final int BALL_STEPS = 20;


    public GameLogic() {
        this.ball = null;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
    }

    public void initialize() {

        ball = new Ball(20, 20, "bomb_green.jpg");

        Enemy enemy1 = new Enemy(350,350, "bomb.jpg",100);
        Enemy enemy2 = new Enemy(150,250, "bomb.jpg",100);
        enemies.add(enemy1);
        enemies.add(enemy2);

        Wall wall1 = new Wall(250, 30, 250, 130, Color.BLACK);
        Wall wall2 = new Wall(100, 50, 150, 50, Color.BLACK);
        Wall wall3 = new Wall(300, 100, 300, 150, Color.BLACK);
       // Wall wall4 = new Wall(100, 50, 150, 50, Color.BLACK);
       // Wall wall5 = new Wall(100, 50, 150, 50, Color.BLACK);
       // Wall wall6 = new Wall(100, 50, 150, 50, Color.BLACK);
       // Wall wall7 = new Wall(100, 50, 150, 50, Color.BLACK);
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
       // walls.add(wall4);
       // walls.add(wall5);
        //walls.add(wall6);
       // walls.add(wall7);
    }

    public void update() {
        //ball.move(2, Direction.RIGHT);
        for (Enemy enemy: enemies) {
            int differenceX = Math.abs(ball.getCoord().x - enemy.getCoord().x);
            int differenceY = Math.abs(ball.getCoord().y - enemy.getCoord().y);
            boolean isEnemyCollided = enemy.isEnemyCollided(walls);

            if (differenceX > differenceY) {
                // Direction LEFT, RIGHT
                if (ball.getCoord().x - enemy.getCoord().x > 0 || isEnemyCollided) {
                    // Direction RIGHT
                    enemy.move(ENEMY_STEPS, Direction.RIGHT);
                } else {
                    // Direction LEFT
                    enemy.move(ENEMY_STEPS, Direction.LEFT);
                }
            } else {
                // Direction UP, DOWN
                if (ball.getCoord().y - enemy.getCoord().y > 0 || isEnemyCollided) {
                    // Direction DOWN
                    enemy.move(ENEMY_STEPS, Direction.DOWN);
                } else {
                    // Direction UP
                    enemy.move(ENEMY_STEPS, Direction.UP);
                }
            }
        }
        for (Wall wall: walls) {
            if (ball.isCollided(wall.getRectangle())){
                wall.inactivate();
            }
        }
    }

    public boolean predictCollision(Direction direction) {
        Rectangle moveRectangle = new Rectangle();
        switch (direction) {
            case RIGHT -> {
                moveRectangle = new Rectangle(ball.getX() + BALL_STEPS, ball.getY(), ball.getWidth(), ball.getHeight());
            }
            case LEFT -> {
                moveRectangle = new Rectangle(ball.getX() - BALL_STEPS, ball.getY(), ball.getWidth(), ball.getHeight());
            }
            case UP -> {
                moveRectangle = new Rectangle(ball.getX(), ball.getY() - BALL_STEPS, ball.getWidth(), ball.getHeight());
            }
            case DOWN -> {
                moveRectangle = new Rectangle(ball.getX(), ball.getY() + BALL_STEPS, ball.getWidth(), ball.getHeight());
            }

        }
        for (Wall wall:walls) {
            if (moveRectangle.intersects(wall.getRectangle())){
                return true;
            }
        }
        return false;


    }

    public ArrayList<Enemy> getEnemy() {
        return enemies;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void movePlayer(Direction direction) {
        ball.move(BALL_STEPS, direction);

    }
}
