package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;


public class Snake extends GameEntity implements Animatable {
    private float speed = 2;
    private int health = 100;
    private KeyCode turnLeftKey, turnRightKey, shootingKey;
    private String name;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public void speedUp() {
        speed++;
    }


    public void shooting(SnakeControl shooting) {
        if (shooting.equals(SnakeControl.SHOOTING)) {
            setImage(Globals.getInstance().getImage("Shooting"));
            setPosition(getPosition());
        }
    }

    public Snake(Vec2d position, String name) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        this.name = name;
        addPart(4);
    }

    public void step() {

    }

    public void stepSnake1() {
        SnakeControl turnDir = getUserInputSnake1();
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();
        body.doPendingModifications();
    }

    public void stepSnake2() {
        SnakeControl turnDir = getUserInputSnake2();
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();
        body.doPendingModifications();
    }

    private SnakeControl getUserInputSnake1() {
        SnakeControl turnDir = SnakeControl.INVALID;

        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;

        return turnDir;
    }

    private SnakeControl getUserInputSnake2() {
        SnakeControl turnDir = SnakeControl.INVALID;

        if(InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;

        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            Game.spawnGameOver(1);
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for (GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if (result != null) return result;
        return head;
    }

    public DelayedModificationList<GameEntity> getBody() {
        return body;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }

    public SnakeHead getHead() {
        return head;
    }
}
