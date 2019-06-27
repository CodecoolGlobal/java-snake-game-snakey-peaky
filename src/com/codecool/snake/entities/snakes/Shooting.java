package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import javafx.geometry.Point2D;


public class Shooting extends GameEntity implements Interactable, Animatable {


    private Point2D heading;
    private static final int SPEED = 6;
    private Snake snake;


    Shooting(Snake snake) {
        this.snake = snake;
        if (snake.getName().equals("Fire")) {
            setImage(Globals.getInstance().getImage("Fireball"));
            heading = Utils.directionToVector(snake.getHead().getRotate(), snake.getSpeed() + SPEED);
            setX(snake.getHead().getPosition().x);
            setY(snake.getHead().getPosition().y);
        }

        else {
            setImage(Globals.getInstance().getImage("Iceball"));
            heading = Utils.directionToVector(snake.getHead().getRotate(), snake.getSpeed() + SPEED);
            setX(snake.getHead().getPosition().x);
            setY(snake.getHead().getPosition().y);
        }
    }

    public Snake getSnake() {
        return snake;
    }

    @Override
    public void step() {
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }


    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            destroy();
            System.out.println(getMessage());
        }
        if (entity instanceof SnakeBody) {
            Snake targetedSnake = ((SnakeBody) entity).getSnake();
            if (!targetedSnake.getName().equals(this.getSnake().getName())) {
                System.out.println(getMessage());
                if (targetedSnake.getSpeed() > 1) {
                        targetedSnake.setSpeed((float) -0.7);
                    }
                destroy();
                }
            }
        }

    @Override
    public String getMessage() {
        return null;
    }
}