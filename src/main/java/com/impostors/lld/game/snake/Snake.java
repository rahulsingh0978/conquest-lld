package com.impostors.lld.game.snake;

import java.util.List;

public record Snake(Position head, Direction dir, List<Position> sections) {
}
