package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;

public class HealthBar extends GameEntity {
    private final int MAX_HEALTH = 100;
    public static int snakeCounter = 0;
    private int health = 100;
    private Snake snake;

    public HealthBar() {
        if (snakeCounter < 1) {
            setImage(Globals.getInstance().getImage("Health100"));
            setX(15);
            setY(15);
            snakeCounter++;
        } else {
            setImage(Globals.getInstance().getImage("Health100"));
            setX(1585);
            setY(15);
        }
    }

    public void changeHealthBar() {
        setImage(Globals.getInstance().getImage("Health" + getHealth()));
    }

    public Snake getSnake() {
        return snake;
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int health) {
        this.health += health;
    }
}
