package models;

import java.util.ArrayList;
import java.util.List;

public class SnakePlayer {
    private List<int[]> body;
    private int direction; // 0: up, 1: right, 2: down, 3: left

    public SnakePlayer(int x, int y) {
        // Design by contract
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordenades del SnakePlayer no poden ser negatives");
        }
        body = new ArrayList<>();
        body.add(new int[]{x, y});
        direction = 1;
    }

    public void restart(int x, int y) {
        // Design by contract
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordenades del SnakePlayer no poden ser negatives");
        }
        body = new ArrayList<>();
        body.add(new int[]{x, y});
        direction = 1;
    }
    
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

    public void grow() {
        int[] tail = body.get(body.size() - 1);
        body.add(new int[]{tail[0], tail[1]});
    }

    public boolean collidesWithWall(SnakeBoard board) {
        int[] head = body.get(0);
        return head[0] < 0 || head[0] >= board.getWidth() || head[1] < 0 || head[1] >= board.getHeight();
    }

    public boolean collidesWithSelf() {
        int[] head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head[0] == body.get(i)[0] && head[1] == body.get(i)[1]) {
                return true;
            }
        }
        return false;
    }

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
    
    public void setDirection(int direction) {
        // Design by contract
        if (direction < 0 || direction > 3) {
            throw new IllegalArgumentException("Direction ha de ser un valor entre 0 i 3");
        }
        // Check if the new direction is not the opposite of the current direction
        if ((this.direction == 0 && direction != 2) ||
            (this.direction == 1 && direction != 3) ||
            (this.direction == 2 && direction != 0) ||
            (this.direction == 3 && direction != 1)) {
            this.direction = direction;
        }
    }
}