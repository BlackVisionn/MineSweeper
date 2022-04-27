package Model;

import java.util.ArrayList;
import java.util.Random;

public class Coord
{
    private static CellPosition size;
    private static ArrayList<CellPosition> allCoords;
    private static Random random = new Random();


    public static void setSize (CellPosition _size)
    {
        size = _size;
        allCoords = new ArrayList<CellPosition>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new CellPosition(x,y));
    }

    public static CellPosition getSize()
    {
        return size;
    }

    public static ArrayList<CellPosition> getAllCoords ()
    {
        return allCoords;
    }

    static boolean inRange (CellPosition cellPos)
    {
        return cellPos.x >= 0 && cellPos.x < size.x &&
                cellPos.y >= 0 && cellPos.y < size.y;
    }

    static CellPosition getRandomCellPos ()
    {
        return new CellPosition(random.nextInt(size.x), random.nextInt(size.y));
    }

    public static ArrayList<CellPosition> getCellPositionsAround (CellPosition cellPos)
    {
        CellPosition around;
        ArrayList<CellPosition> list = new ArrayList<CellPosition>();
        for (int x = cellPos.x - 1; x <= cellPos.x + 1; x++)
            for (int y = cellPos.y - 1; y <= cellPos.y + 1; y++)
                if (inRange(around = new CellPosition(x, y)))
                    if (!around.equals(cellPos))
                        list.add(around);
        return list;
    }
}
