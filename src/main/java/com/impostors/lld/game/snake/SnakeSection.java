package com.impostors.lld.game.snake;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SnakeSection {

    private final int[] dirX = {0, 0, -1, 1};
    private final int[] dirY = {-1, 1, 0, 0};

    private Position head;
    private Position tail;
    Direction headDirection;

    public void move(Direction direction) {
        int headX = head.x() + dirX[direction.ordinal()];
        int headY = head.y() + dirY[direction.ordinal()];

        int tailX = head.x() + dirX[headDirection.ordinal()];
        int tailY = head.y() + dirY[headDirection.ordinal()];

        headDirection = direction;
        head = new Position(headX, headY);
        tail = new Position(tailX, tailY);
    }
}
