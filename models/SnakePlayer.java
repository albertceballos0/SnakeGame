package models;

import java.util.ArrayList;
import java.util.List;

public class SnakePlayer {
    private List<int[]> body;
    private int direction; // 0: up, 1: right, 2: down, 3: left

    /**
     * Constructs a new SnakePlayer with the initial position specified by the given coordinates.
     *
     * @param x the initial x-coordinate of the snake's head
     * @param y the initial y-coordinate of the snake's head
     */
    public SnakePlayer(int x, int y) {
        body = new ArrayList<>();
        body.add(new int[]{x, y});
        direction = 1;
    }

    /**
     * Moves the snake in the current direction.
     * <p>
     * The snake's head is moved one step in the direction specified by the 
     * {@code direction} field. The direction is represented as follows:
     * <ul>
     *   <li>0 - up</li>
     *   <li>1 - right</li>
     *   <li>2 - down</li>
     *   <li>3 - left</li>
     * </ul>
     * The new head position is added to the front of the snake's body, and the 
     * last segment of the body is removed to maintain the snake's length.
     * </p>
     */
    public void move() {
        int[] newHead = {body.get(0)[0], body.get(0)[1]};
        switch (direction) {
            case 0: newHead[1]--; break; // up
            case 1: newHead[0]++; break; // right
            case 2: newHead[1]++; break; // down
            case 3: newHead[0]--; break; // left
        }
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    /**
     * Increases the size of the snake by adding a new segment at the position of the last segment.
     * This method retrieves the coordinates of the last segment of the snake's body and appends
     * a new segment with the same coordinates to the end of the body.
     */
    public void grow() {
        int[] tail = body.get(body.size() - 1);
        body.add(new int[]{tail[0], tail[1]});
    }

    /**
     * Checks if the snake's head collides with the wall of the board.
     *
     * @param board The SnakeBoard object representing the game board.
     * @return true if the snake's head is outside the boundaries of the board, false otherwise.
     */
    public boolean collidesWithWall(SnakeBoard board) {
        int[] head = body.get(0);
        return head[0] < 0 || head[0] >= board.getWidth() || head[1] < 0 || head[1] >= board.getHeight();
    }

    /**
     * Checks if the snake collides with itself.
     * 
     * This method iterates through the body of the snake, starting from the second segment,
     * and checks if any segment has the same coordinates as the head of the snake.
     * 
     * @return true if the head of the snake collides with any other segment of its body, false otherwise.
     */
    public boolean collidesWithSelf() {
        int[] head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head[0] == body.get(i)[0] && head[1] == body.get(i)[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the snake occupies a given position (x, y).
     *
     * @param x the x-coordinate of the position to check
     * @param y the y-coordinate of the position to check
     * @return true if the snake occupies the position (x, y), false otherwise
     */
    public boolean occupies(int x, int y) {
        for (int[] segment : body) {
            if (segment[0] == x && segment[1] == y) {
                return true;
            }
        }
        return false;
    }

    // Getters and setters
    public int getX() { return body.get(0)[0]; }
    public int getY() { return body.get(0)[1]; }
    public List<int[]> getBody() { return body; }
    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }
}