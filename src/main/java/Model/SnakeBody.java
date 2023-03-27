package Model;

public class SnakeBody
{
    private int x;
    private int y;

    public SnakeBody(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //region getter/setter
    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
    //endregion
}
