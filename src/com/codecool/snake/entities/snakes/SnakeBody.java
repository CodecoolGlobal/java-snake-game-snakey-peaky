package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import java.util.LinkedList;
import java.util.Queue;

import com.codecool.snake.entities.Interactable;
import com.sun.javafx.geom.Vec2d;


public class SnakeBody extends GameEntity implements Interactable{
    private Queue<Vec2d> history = new LinkedList<>();
    public static int historySize = 1;
    private Snake snake;


    public SnakeBody(Vec2d coord) {
        setX(coord.x);
        setY(coord.y);

        for (int i = 0; i < historySize; i++) {
            history.add(coord);
        }
    }

    void setSnakeBodyImage(Snake snake) {
        if (snake.getName().equals("Fire")) {
            setImage(Globals.getInstance().getImage("SnakeBodyFire"));
        }
        else if (snake.getName().equals("Ice")) {
            setImage(Globals.getInstance().getImage("SnakeBodyIce"));
        }
    }

    @Override
    public void setPosition(Vec2d pos) {
        Vec2d currentPos = history.poll(); // remove the oldest item from the history
        setX(currentPos.x);
        setY(currentPos.y);
        history.add(pos); // add the parent's current position to the beginning of the history
    }

    @Override
    public void apply(GameEntity entity) {
        /*try {
            if (entity instanceof SnakeHead) {
                if (!((SnakeHead) entity).getSnake().getName().equals(snake.getName())) {
                    System.out.println("Game over");
                }
            }
        }
        catch (NullPointerException ex) {}*/
    }

    @Override
    public String getMessage() {
        return null;
    }

    public Snake getSnake() {
        return snake;
    }
}