package mock_classes;

import models.SnakeBoard;

public class SnakeBoardMock extends SnakeBoard{
    private int width;
    private int height;

    public SnakeBoardMock(int width, int height) {
        super(width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
