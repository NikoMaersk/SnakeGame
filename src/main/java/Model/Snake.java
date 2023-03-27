package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake
{
    ArrayList<SnakeBody> body;
    GameBoard gb;
    Direction direction;

    public Snake(int startX, int startY, Direction startDirection, GameBoard gb)
    {
        // Initialize the snake with a head segment at the given position and direction
        body = new ArrayList<>();
        body.add(new SnakeBody(startX, startY));
        direction = startDirection;
        this.gb = gb;
    }

    public void move()
    {
        // Move the snake one step in the current direction
        int dx = 0, dy = 0;
        switch (direction)
        {
            case UP:
                dy = -1;
                break;
            case DOWN:
                dy = 1;
                break;
            case LEFT:
                dx = -1;
                break;
            case RIGHT:
                dx = 1;
                break;
        }

        // Move the body segments in reverse order so that each segment follows the one in front of it
        for (int i = body.size() - 1; i > 0; i--)
        {
            SnakeBody segment = body.get(i);
            SnakeBody frontSegment = body.get(i - 1);
            segment.setX(frontSegment.getX());
            segment.setY(frontSegment.getY());
        }

        // Move the head segment
        SnakeBody headSegment = body.get(0);
        headSegment.setX(headSegment.getX() + dx);
        headSegment.setY(headSegment.getY() + dy);
    }

    public void grow()
    {
        // Add a new body segment to the end of the snake
        SnakeBody tailSegment = body.get(body.size() - 1);
        int x = tailSegment.getX();
        int y = tailSegment.getY();
        body.add(new SnakeBody(x, y));
    }

    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.BLUE);
        for (SnakeBody segment : body)
        {
            gc.fillOval(segment.getX() * gb.getTileSize(), segment.getY() * gb.getTileSize(), gb.getTileSize(), gb.getTileSize());
        }
    }

    public void remove(GraphicsContext gc)
    {
        for (SnakeBody segment : body)
        {
            gc.clearRect(segment.getX() * gb.getTileSize(), segment.getY() * gb.getTileSize(), gb.getTileSize(), gb.getTileSize());
        }
    }

    public boolean checkWallCollision()
    {
        double headX = body.get(0).getX() * gb.getTileSize();
        double headY = body.get(0).getY() * gb.getTileSize();

        return headX < 0 || headX > (gb.getBoardWidth() - gb.getTileSize()) || headY < 0 || headY > (gb.getBoardHeight() - gb.getTileSize());
    }

    public boolean checkFoodCollision(Food food)
    {
        double headX = body.get(0).getX() * gb.getTileSize();
        double headY = body.get(0).getY() * gb.getTileSize();

        if (headX == food.getX() && headY == food.getY())
        {
            return true;
        }
        return false;
    }

    //region Getter/setter
    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        if (direction != this.direction.getOpposite())
        {
            this.direction = direction;
        }
    }

    public ArrayList<SnakeBody> getBody()
    {
        return body;
    }
    //endregion
}
