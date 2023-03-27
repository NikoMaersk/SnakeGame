package Model;

public enum Direction
{
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Direction getOpposite()
    {
        Direction oppositeDirection = null;
        switch (this)
        {
            case UP:
                oppositeDirection = DOWN;
                break;
            case DOWN:
                oppositeDirection = UP;
                break;
            case LEFT:
                oppositeDirection = RIGHT;
                break;
            case RIGHT:
                oppositeDirection = LEFT;
                break;
        }
        return oppositeDirection;
    }
}

