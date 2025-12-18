package com.impostors.lld.game.snake;

import java.util.ArrayList;

public class GameExecutor {

    public static void main(String[] args) {
        SnakeGameImpl game = new SnakeGameImpl(10, 10, new ArrayList<>());
        var state = game.move(Direction.RIGHT);
    }
}
