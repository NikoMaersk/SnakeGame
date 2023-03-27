package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake
{
    private ArrayList<SnakeBody> body;
    private Direction direction;
    private final int STEP;

    public Snake(int startX, int startY, int step, Direction startDirection)
    {
        body = new ArrayList<>();
        body.add(new SnakeBody(startX, startY));
        direction = startDirection;
        this.STEP = step;
    }

    public void move()
    {
        int dx = 0, dy = 0;
        switch (direction)
        {
            case UP:
                dy = -STEP;
                break;
            case DOWN:
                dy = STEP;
                break;
            case LEFT:
                dx = -STEP;
                break;
            case RIGHT:
                dx = STEP;
                break;
        }

        for (int i = body.size() - 1; i > 0; i--)
        {
            SnakeBody segment = body.get(i);
            SnakeBody frontSegment = body.get(i - 1);
            segment.setX(frontSegment.getX());
            segment.setY(frontSegment.getY());
        }

        SnakeBody headSegment = body.get(0);
        headSegment.setX(headSegment.getX() + dx);
        headSegment.setY(headSegment.getY() + dy);
    }

    public void grow()
    {
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
            gc.fillOval(segment.getX(), segment.getY(), STEP, STEP);
        }
    }

    public void erase(GraphicsContext gc)
    {
        for (SnakeBody segment : body)
        {
            gc.clearRect(segment.getX(), segment.getY(), STEP, STEP);
        }
    }

    public boolean checkWallCollision(int width, int height)
    {
        double headX = body.get(0).getX();
        double headY = body.get(0).getY();

        return headX < 0 || headX > width || headY < 0 || headY > height;
    }

    public boolean checkFoodCollision(Food food)
    {
        double headX = body.get(0).getX();
        double headY = body.get(0).getY();

        return headX == food.getX() && headY == food.getY();
    }

    public boolean checkSnakeCollision()
    {
        if (body.size() > 1)
        {
            double headX = body.get(0).getX();
            double headY = body.get(0).getY();

            for (int i = 1; i < body.size() - 1; i++)
            {
                if (headX == body.get(i).getX() && headY == body.get(i).getY())
                {
                    return true;
                }
            }
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
