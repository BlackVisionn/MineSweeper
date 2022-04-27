package Model;

public class CellPosition {
    public int x;
    public int y;

    public CellPosition (int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){

        if(obj instanceof CellPosition){
            CellPosition position = (CellPosition)obj;
            return position.x == x && position.y == y;
        }
        return super.equals(obj);
    }
}
