package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;

public class Healthbar extends GameEntity {
    private final int MAX_HEALTH = 100;
    public static int snakeCounter = 0;
    private Snake snake;

    public Healthbar() {
        if (snakeCounter < 1) {
            setImage(Globals.getInstance().getImage("FullHealth"));
            setX(15);
            setY(15);
            snakeCounter++;
        } else {
            setImage(Globals.getInstance().getImage("FullHealth"));
            setX(1585);
            setY(15);
        }
    }

    public Snake getSnake() {
        return snake;
    }


}
