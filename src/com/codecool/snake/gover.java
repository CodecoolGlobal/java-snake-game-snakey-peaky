package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

public class gover extends GameEntity{
    public gover() {
        setImage(Globals.getInstance().getImage("gameOverScene"));
        setX(100);
        setY(50);
    }
}