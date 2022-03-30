package sweeper;

public class Cell {

    private CellState [] [] cellStatesMap;

    ///СОСТОЯНИЯ ЯЧЕЙКИ
    Cell (CellState cellState){
        cellStatesMap = new CellState[Coord.getSize().x][Coord.getSize().y];
        for (CellPosition cellPos : Coord.getAllCoords()){
            cellStatesMap [cellPos.x] [cellPos.y] = cellState;
        }
    }

    CellState getCellState (CellPosition cellPos){

        if(Coord.inRange(cellPos)){
            return cellStatesMap [cellPos.x] [cellPos.y];
        }
        return null;
    }

    void setCellState (CellPosition cellPos, CellState cellState){

        if(Coord.inRange(cellPos)){
            cellStatesMap [cellPos.x] [cellPos.y] = cellState;
        }
    }
}
