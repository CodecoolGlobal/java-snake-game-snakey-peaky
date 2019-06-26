package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;


public class Game extends Pane {
    private Snake snake = null;
    private Snake snake2 = null;
    private GameTimer gameTimer = new GameTimer();
    public Button restartBtn;


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake, snake2);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
        System.out.println(gameTimer.getTimer());;
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
        restartBtn = new Button("Restart");
        restartBtn.setLayoutX(480);
        restartBtn.setLayoutY(20);
        restartBtn.setStyle("-fx-font-size: 18;");
        getChildren().add(restartBtn);
        restartBtn.setOnAction(event -> {
            Globals.getInstance().stopGame();
            Globals.getInstance().display.clear();
            init();
            start();
        });
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500), "Milán");
        snake2 = new Snake(new Vec2d(200, 500), "Vitya");
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
