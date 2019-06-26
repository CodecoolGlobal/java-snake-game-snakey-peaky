package com.codecool.snake.resources;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

public class Styling extends GameEntity {
    public Styling() {
        setImage(Globals.getInstance().getImage("BG"));
        setX(0);
        setY(0);
    }
}
