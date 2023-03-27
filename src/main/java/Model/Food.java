package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Food
{
    private int x;
    private int y;
    private int radius;
    private Circle circle;

    public Food(int x, int y, int radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        circle = new Circle(x + radius / 2.0, y + radius / 2.0, radius / 2.0, Color.RED);
    }

    public void draw(GraphicsContext gc)
    {
        circle.setCenterX(x + radius / 2.0);
        circle.setCenterY(y + radius / 2.0);
        gc.setFill(circle.getFill());
        gc.fillOval(x, y, radius, radius);
    }

    public void erase(GraphicsContext gc)
    {
        circle.setCenterX(x + radius / 2.0);
        circle.setCenterY(y + radius / 2.0);
        gc.setFill(circle.getFill());
        gc.clearRect(x, y, radius, radius);
    }

    public void randomizePosition(GameBoard gb)
    {
        this.x = (int) (Math.random() * (gb.getBoardWidth() / gb.getTileSize())) * gb.getTileSize();
        this.y = (int) (Math.random() * (gb.getBoardHeight() / gb.getTileSize())) * gb.getTileSize();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
