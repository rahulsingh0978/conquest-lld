package com.impostors.lld.game.snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeGameImpl implements SnakeGame {

    // l, r, u, d
    private final int[] dirX = {0, 0, -1, 1};
    private final int[] dirY = {-1, 1, 0, 0};
    private final int m, n;
    private final List<Position> foodPositions;
    private final SnakePosition snakePosition;
    private GameState state;

    public SnakeGameImpl(int m, int n, List<Position> foodPositions) {
        this.m = m;
        this.n = n;
        if (m < 1 || n < 1) {
            throw new IllegalArgumentException("Invalid boundaries set");
        }
        this.foodPositions = foodPositions;
        state = GameState.PLAYING;
        ArrayList<SnakeSection> snakeSections = new ArrayList<>(); // ---
                                                            // (0,0 0,5, r) (0,5, 1,5, d) (1,5 2,5, r)
                                                            // all indices
        snakeSections.add(new SnakeSection(new Position(1,1), new Position(0,1), Direction.RIGHT));
        snakePosition = new SnakePosition(snakeSections, 1);
    }

    @Override
    public GameState move(Direction dir) {
        if (state == GameState.END) {
            throw new IllegalStateException("Game End. Please start a new game");
        }
        boolean foodConsumed = checkIfFoodConsumed(dir);
        moveSnake(foodConsumed);    
        if (checkForCollissions()) {
            state = GameState.END;
            return state;
        };
        return GameState.PLAYING;
    }

    private boolean checkIfFoodConsumed(Direction direction) {
        SnakeSection headSection = snakePosition.snakeSections().get(0);
        headSection.move(direction);
        if (foodPositions.contains(headSection.getHead())) {
            foodPositions.remove(headSection.getHead());
            return true;
        }
        return false;
    }

    private boolean checkForCollissions() {
        return false;
    }

    private void moveSnake(boolean foodConsumed) {
    }
}
