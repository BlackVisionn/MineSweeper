package sweeper;
// нужно поставить private
public class GameField {
    private Cell bombCell;
    private Cell closedCell;
    private int totalBombs;
    private int totalFlags;
    private int closedCellsCount;
    private GameState gameState;

    GameField (int totalBombs){
        this.totalBombs = totalBombs;
        totalFlags = totalBombs;
        fixBombsCount();
    }

    void createField(){
        gameState = GameState.PLAY;
        bombCell = new Cell(CellState.ZERO);
        closedCell = new Cell(CellState.CLOSED);
        closedCellsCount = Coord.getSize().x * Coord.getSize().y;
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
        for (CellPosition aroundPos : Coord.getCellPositionsAround(cellPos)){
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
        closedCellsCount--;
    }

    public void setFlagToCell(CellPosition cellPos) {

        switch (closedCell.getCellState(cellPos)){

            case FLAGED : closedCell.setCellState(cellPos, CellState.CLOSED);
            case CLOSED : closedCell.setCellState(cellPos, CellState.FLAGED);
        }
    }

    public void openCell (CellPosition cellPos){

        switch (getClosedCellState(cellPos)){
            case OPENED : return;
            case FLAGED : return;
            case CLOSED : switch (getBombCellState(cellPos)){
                case ZERO : openCellsAround(cellPos); return;
                case BOMB : openBombs(cellPos); return;
                default : setOpenedToCell(cellPos); return;
            }
        }
    }

    private void openCellsAround(CellPosition cellPos){

        setOpenedToCell(cellPos);
        for(CellPosition aroundPos : Coord.getCellPositionsAround(cellPos)){
            openCell(aroundPos);
        }
    }

    public int getTotalBombs() {

        return totalBombs;
    }

    public int getClosedCellsCount() {

        return closedCellsCount;
    }

    private void openBombs(CellPosition cellPos){

        gameState = GameState.BOMBED;
        closedCell.setCellState(cellPos, CellState.BOMBED);
        for (CellPosition otherPos : Coord.getAllCoords()){
            if(getBombCellState(otherPos) == CellState.BOMB){
                // открыть клетку с закрытой бомбой
                if(closedCell.getCellState(otherPos) == CellState.CLOSED){
                    closedCell.setCellState(otherPos, CellState.OPENED);
                }
            }
            else {
                // поставить нету бомбы на ячейке с флажком
                if (closedCell.getCellState(otherPos) == CellState.FLAGED){
                    closedCell.setCellState(otherPos, CellState.NOBOMB);
                }
            }
        }
    }

    public GameState getGameState(){
        return gameState;
    }
}
