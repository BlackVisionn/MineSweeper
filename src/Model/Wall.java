package Model;

public class Wall {

    private Cell closedCell;
    private boolean direction;

    Wall (Cell closedCell, boolean direction){
        this.closedCell = closedCell;
        this.direction = direction;
    }

    public void openWall (CellPosition cellPos){

        closedCell.setOpenedToCell(cellPos);
        // Если стена расположена вертикально
        if(direction){
            for (CellPosition wallPos : Coord.getWallCellPositions(cellPos, direction)){
                closedCell.setOpenedToCell(wallPos);
            }
        }
        // Иначе стена расположена горизонтально
        else {
            for (CellPosition wallPos : Coord.getWallCellPositions(cellPos, direction)){
                closedCell.setOpenedToCell(wallPos);
            }
        }
    }
}
