package com.impostors.lld.game.snake;

public enum Direction {
    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,1),
    DOWN(0,-1);

    private final int dirX;
    private final int dirY;

    Direction(int dirX, int dirY) {
        this.dirX = dirX;
        this.dirY = dirY;
    }
}
