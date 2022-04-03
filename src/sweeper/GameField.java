package sweeper;
// нужно поставить private
public class GameField {
    private Cell bombCell;
    private Cell closedCell;
    private int totalBombs;
    private int totalFlags;

    GameField (int totalBombs){
        this.totalBombs = totalBombs;
        totalFlags = totalBombs;
        fixBombsCount();
    }

    void createField(){
        bombCell = new Cell(CellState.ZERO);
        closedCell = new Cell(CellState.CLOSED);
        for (int i = 0; i<totalBombs; i++){
            placeBomb();
        }
    }

    private void placeBomb(){
        while (true){
            CellPosition cellPos = Coord.getRandomCellPos();
            if(bombCell.getCellState(cellPos) == CellState.BOMB){
                continue;
            }
            bombCell.setCellState(cellPos, CellState.BOMB);
            incNumbersAroundBomb(cellPos);
            break;
        }
    }

    private void fixBombsCount(){
        int maxBombs = Coord.getSize().x * Coord.getSize().y / 2;
        if(totalBombs > maxBombs){
            totalBombs = maxBombs;
        }
    }

    private void incNumbersAroundBomb(CellPosition cellPos){
        for (CellPosition aroundPos : Coord.getCellPositionsArround(cellPos)){
            if (CellState.BOMB != bombCell.getCellState(aroundPos)){
                bombCell.setCellState(aroundPos, bombCell.getCellState(aroundPos).getNextNumber());
            }
        }
    }

    CellState getBombCellState (CellPosition cellPos){

        return bombCell.getCellState(cellPos);
    }

    CellState getClosedCellState (CellPosition cellPos){
        return closedCell.getCellState(cellPos);
    }


    public void setOpenedToCell(CellPosition cellPos) {
        closedCell.setCellState(cellPos, CellState.OPENED);
    }

    public void setFlagToCell(CellPosition cellPos) {
        closedCell.setCellState(cellPos, CellState.FLAGED);
    }
}
