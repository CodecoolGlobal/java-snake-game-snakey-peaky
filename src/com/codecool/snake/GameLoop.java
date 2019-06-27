package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.util.Duration;

import java.util.List;

public class GameLoop {
    private Snake snake;
    private Snake snake2;
    private boolean running = false;

    public GameLoop(Snake snake/*, Snake snake2*/) {
        this.snake = snake;
        this.snake2 = snake2;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            snake.stepSnake1();
            //snake2.stepSnake2();
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
        }
        Globals.getInstance().display.frameFinished();
    }


    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
