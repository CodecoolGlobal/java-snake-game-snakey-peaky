package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.HealthBar;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;


public class Snake extends GameEntity implements Animatable {
    private float speed = 3;
    private int fireShootingInterval;
    private int iceShootingInterval;
    private String name;
    private HealthBar healthBar;
    private Shooting shooting;
    private int availableShotsLeft = 0;


    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public void speedUp() {
        speed = (float) (speed + 0.5);
    }

    public Snake(Vec2d position, String name) {
        healthBar = new HealthBar();
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        this.name = name;
        head.setSnakeHeadImage(this);
        addPart(20);
    }

    public void step() {

    }

    public void stepSnake1() {
        SnakeControl turnDir = getUserInputSnake1();
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();
        body.doPendingModifications();
        fireShootingInterval-= (fireShootingInterval > 0) ? 1 : 0;
    }

    public void stepSnake2() {
        SnakeControl turnDir = getUserInputSnake2();
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();
        body.doPendingModifications();
        iceShootingInterval -= (iceShootingInterval > 0) ? 1 : 0;
    }

    private SnakeControl getUserInputSnake1() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.W)) {
            if (availableShotsLeft > 0) {
                if (fireShootingInterval == 0) {
                    new Shooting(this);
                    fireShootingInterval = 20;
                    availableShotsLeft--;
                }
            }
        }

        if (InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;

        return turnDir;
    }

    private SnakeControl getUserInputSnake2() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.UP)) {
            if (availableShotsLeft > 0) {
                if (iceShootingInterval == 0) {
                    new Shooting(this);
                    iceShootingInterval = 20;
                    availableShotsLeft--;
                }
            }
        }

        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;

        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position, this);
            newBodyPart.setSnakeBodyImage(this);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        healthBar.changeHealth(diff);
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || healthBar.getHealth() <= 0) {
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
        return healthBar.getHealth();
    }

    public String getName() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed += speed;
    }

    public SnakeHead getHead() {
        return head;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setAvailableShotsLeft(int plusShots) {
        this.availableShotsLeft += plusShots;
    }
}
