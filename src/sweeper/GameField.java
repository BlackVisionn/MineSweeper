package sweeper;
// нужно поставить private
public class GameField {
    private Cell cell;
    private int totalBombs;

    GameField (int totalBombs){
        this.totalBombs = totalBombs;
    }

    void createField(){
        cell = new Cell(CellState.ZERO);
        placeBomb();
    }

    private void placeBomb(){
        cell.setCellState(new CellPosition(4,4), CellState.BOMB);
    }

    CellState get (CellPosition cellPos){
        return cell.getCellState(cellPos);
    }

}
