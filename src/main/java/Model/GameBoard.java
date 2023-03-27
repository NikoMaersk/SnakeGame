package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameBoard
{
    private int boardWidth;
    private int boardHeight;
    private int tileSize;
    private Color tileColor;
    private Color borderColor;

    public GameBoard(int boardWidth, int boardHeight, int tileSize) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tileSize = tileSize;
        this.tileColor = Color.GREEN;
        this.borderColor = Color.DARKGREEN;
    }

    public void drawBoard(GraphicsContext gc)
    {
        gc.setFill(tileColor);
        gc.setStroke(borderColor);
        gc.setLineWidth(1);

        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                double xPos = x * tileSize;
                double yPos = y * tileSize;

                gc.fillRect(xPos, yPos, tileSize, tileSize);
            }
        }
    }

    public int getTileSize()
    {
        return tileSize;
    }

    public int getBoardWidth()
    {
        return boardWidth;
    }

    public int getBoardHeight()
    {
        return boardHeight;
    }
}
