package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1920;
    public static final double WINDOW_HEIGHT = 1080;

    public Display display;
    public Game game;
    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHeadFire", new Image("snake_head_fire.png"));
        resources.addImage("SnakeHeadIce", new Image("snake_head_ice.png"));
        resources.addImage("SnakeBodyFire", new Image("snake_body_fire.png"));
        resources.addImage("SnakeBodyIce", new Image("snake_body_ice.png"));
        resources.addImage("Nightking", new Image("nightking.png"));
        resources.addImage("DragonFruit", new Image("dragon_fruit.png"));
        resources.addImage("Khaleesi", new Image("khaleesi.png"));
        resources.addImage("ShootingPowerUp", new Image("shootingpowerup.png"));
        resources.addImage("Fireball", new Image("fireball.png"));
        resources.addImage("Iceball", new Image("iceball.png"));
        resources.addImage("gameOverScene", new Image("GO.png"));
        resources.addImage("BG", new Image("background.png"));
        resources.addImage("Health100", new Image("healthfull.png"));
        resources.addImage("Health90", new Image("health90.png"));
        resources.addImage("Health80", new Image("health80.png"));
        resources.addImage("Health70", new Image("health70.png"));
        resources.addImage("Health60", new Image("health60.png"));
        resources.addImage("Health50", new Image("health50.png"));
        resources.addImage("Health40", new Image("health40.png"));
        resources.addImage("Health30", new Image("health30.png"));
        resources.addImage("Health20", new Image("health20.png"));
        resources.addImage("Health10", new Image("health10.png"));
        resources.addImage("Health0", new Image("health0.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
