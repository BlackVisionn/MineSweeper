package sweeper;
// нужно поставить private
public class GameField {
    private Cell cell;
    private int totalBombs;

    GameField (int totalBombs){
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void createField(){
        cell = new Cell(CellState.ZERO);
        for (int i = 0; i<totalBombs; i++){
            placeBomb();
        }
    }

    private void placeBomb(){
        while (true){
            CellPosition cellPos = Coord.getRandomCellPos();
            if(cell.getCellState(cellPos) == CellState.BOMB){
                continue;
            }
            cell.setCellState(cellPos, CellState.BOMB);
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
            if (CellState.BOMB != cell.getCellState(aroundPos)){
                cell.setCellState(aroundPos, cell.getCellState(aroundPos).getNextNumber());
            }
        }
    }

    CellState get (CellPosition cellPos){
        return cell.getCellState(cellPos);
    }

}
