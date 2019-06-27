package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.ShootingPowerUp;
import com.codecool.snake.entities.powerups.SpeedPowerUp;
import com.codecool.snake.resources.Styling;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


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
        setBackground();
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);
        spawnSpeedPowerUps(3);
        spawnShootingPowerUps(3);
        

        GameLoop gameLoop = new GameLoop(snake, snake2);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
        restartBtn = new Button("Restart");
        restartBtn.setLayoutX(880);
        restartBtn.setLayoutY(960);
        restartBtn.setStyle("-fx-font-size: 18;");
        getChildren().add(restartBtn);
        restartBtn.setOnAction(event -> {
            Globals.getInstance().stopGame();
            Globals.getInstance().display.clear();
            HealthBar.snakeCounter = 0;
            init();
            start();
        });
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(200, 500), "Fire");
        snake2 = new Snake(new Vec2d(1500, 500), "Ice");
    }

    public static void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    public static void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    public static void spawnGameOver(int numberOfGameOver) {
        for(int i = 0; i < numberOfGameOver; ++i) new gover();
    }

    public static void spawnSpeedPowerUps(int numberOfSpeedPowerUps) {
        for(int i = 0; i < numberOfSpeedPowerUps; i++) new SpeedPowerUp();
    }
    public static void spawnShootingPowerUps(int numberOfShootingPowerUps) {
        for(int i = 0; i < numberOfShootingPowerUps; i++) new ShootingPowerUp();
    }

    private void setBackground() {
        Styling bg = new Styling();


    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
