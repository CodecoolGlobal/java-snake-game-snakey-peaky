package com.codecool.snake.entities.gameover;
import com.codecool.snake.Globals;
import javafx.scene.image.ImageView;

public class gameOver extends ImageView{
    public void gameOver() {
        setImage(Globals.getInstance().getImage("gameOverScene"));
        setX(50 * Globals.WINDOW_WIDTH);
        setY(50 * Globals.WINDOW_HEIGHT);
        System.out.println("kepnek meg kene jelennie....csak mondom");
    }

}