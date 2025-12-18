package com.impostors.lld.game.snake.entities;

import com.impostors.lld.game.snake.Direction;
import com.impostors.lld.game.snake.Position;
import com.impostors.lld.game.snake.SnakeSection;

import java.util.List;

/**
 *
 * @param head
 * @param dir
 * @param sections
 */
public record Snake(Position head, Direction dir, List<SnakeSection> sections) {
}
