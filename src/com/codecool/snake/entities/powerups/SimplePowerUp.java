package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp() {
        double Ycoord;
        double Xcoord;
        setImage(Globals.getInstance().getImage("DragonFruit"));

        do {
            Xcoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        }
        while (Xcoord > 1750);
        do {
            Ycoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        }
        while (Ycoord > 970);
        setY(Ycoord);
        setX(Xcoord);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            Game.spawnPowerUps(1);
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
