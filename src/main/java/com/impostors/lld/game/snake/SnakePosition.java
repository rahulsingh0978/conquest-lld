package com.impostors.lld.game.snake;

import java.util.List;

public record SnakePosition(List<SnakeSection> snakeSections, int length) {
}
