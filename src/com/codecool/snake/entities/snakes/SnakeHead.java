package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Game;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedPowerUp;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 6;
    private Snake snake;
    private Shooting shooting;

    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setPosition(position);

    }

    void updateStartingPosition(double Xpos) {
        this.setX(Xpos);
    }

    void setSnakeHeadImage(Snake snake) {
        if (snake.getName().equals("Fire")) {
            setImage(Globals.getInstance().getImage("SnakeHeadFire"));
        }
        else if(snake.getName().equals("Ice")) {
            setImage(Globals.getInstance().getImage("SnakeHeadIce"));
        }
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }


    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage());
            System.out.println(snake.getHealth());
        }

        if (entity instanceof SimplePowerUp) {
            System.out.println(getMessage());
            snake.addPart(4);
            SnakeBody.historySize = 1;
        }

        if (entity instanceof SpeedPowerUp) {
            System.out.println(getMessage());
            snake.speedUp();
        }
        /*if (entity instanceof ShootingPowerUp) {
            System.out.println(getMessage());
                new Shooting(snake);
            }*/

        if (entity instanceof SnakeHead) {
            double entityXCoord = entity.getX();
            double entityYCoord = entity.getY();
            double snakeHeadX = snake.getHead().getX();
            double snakeHeadY = snake.getHead().getY();
            if (Math.hypot(Math.abs(snakeHeadX - entityXCoord), Math.abs(snakeHeadY - entityYCoord)) < 45) {
                Globals.getInstance().stopGame();
                System.out.println("Game over");
                Game.spawnGameOver(1);
            }
        }

        if(entity instanceof SnakeBody) {
            if (!snake.getBody().getList().contains(entity)) {
                double entityXCoord = entity.getX();
                double entityYCoord = entity.getY();
                double snakeHeadX = snake.getHead().getX();
                double snakeHeadY = snake.getHead().getY();
                if (Math.hypot(Math.abs(snakeHeadX - entityXCoord), Math.abs(snakeHeadY - entityYCoord)) < 45) {
                    Globals.getInstance().stopGame();
                    Game.spawnGameOver(1);
                }
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
