package Model;

public class Cell {

    private int closedCellsCount;
    private Cell closedCell;
    private GameField gameField;
    private int placedFlags;
    private int remainingFlagsCount;
    private Bomb bomb;
    private Wall wall;

    private CellState [] [] cellStatesMap;

    // Состояния ячейки
    Cell (CellState cellState){
        cellStatesMap = new CellState[Coord.getSize().x][Coord.getSize().y];
        for (CellPosition cellPos : Coord.getAllCoords()){
            cellStatesMap [cellPos.x] [cellPos.y] = cellState;
        }
    }

    void getCells(GameField field, Cell closedCell, int health){
        this.gameField = field;
        this.closedCell = closedCell;
        closedCellsCount = Coord.getSize().x * Coord.getSize().y;
        remainingFlagsCount = gameField.getTotalBombs();
        bomb = new Bomb(gameField.getTotalBombs(), closedCell, gameField.getBombCell(), health);
        wall = new Wall(closedCell, gameField.getDirection());
    }

    // Открыть ячейку
    public void openCell (CellPosition cellPos){

        switch (closedCell.getCellState(cellPos)){
            case OPENED : openClosedCellsAroundFlag(cellPos); return;
            case FLAGED : return;
            case CLOSED : switch (gameField.getBombCellState(cellPos)){
                case ZERO : openCellsAround(cellPos); return;
                case WALL : wall.openWall(cellPos); return;
                case BOMB : closedCellsCount = bomb.explose(cellPos, closedCellsCount);
                    remainingFlagsCount = bomb.getTotalBombs(); return;
                default : setOpenedToCell(cellPos); return;
            }
        }
    }

    // Открыть ячейку верхнего уровня
    public void setOpenedToCell(CellPosition cellPos) {
        closedCell.setCellState(cellPos, CellState.OPENED);
        closedCellsCount--;
    }

    // Установить флаг на ячейку верхнего уровня
    public void setFlagToCell(CellPosition cellPos) {

        switch (closedCell.getCellState(cellPos)){

            case FLAGED : {
                placedFlags--;
                remainingFlagsCount++;
                closedCell.setCellState(cellPos, CellState.CLOSED);
                return;
            }
            case CLOSED : {
                if(placedFlags < gameField.getTotalBombs() && remainingFlagsCount != 0){
                    placedFlags++;
                    remainingFlagsCount--;
                    closedCell.setCellState(cellPos, CellState.FLAGED);
                }
                return;
            }
        }
    }

    // Открытие пустых ячеек около ячеек с цифрами
    private void openCellsAround(CellPosition cellPos){

        setOpenedToCell(cellPos);
        for(CellPosition aroundPos : Coord.getCellPositionsAround(cellPos)){
            openCell(aroundPos);
        }
    }

    // Открытие ячеек вокруг флага
    private void openClosedCellsAroundFlag (CellPosition cellPos){

        if(gameField.getBombCellState(cellPos) != CellState.BOMB){
            int count = 0;
            for (CellPosition aroundPos : Coord.getCellPositionsAround(cellPos)){
                if(closedCell.getCellState(aroundPos) == CellState.FLAGED){
                    count++;
                }
            }
            if (count == gameField.getBombCellState(cellPos).getNumber()){
                for (CellPosition aroundPos : Coord.getCellPositionsAround(cellPos)){
                    if(closedCell.getCellState(aroundPos) == CellState.CLOSED){
                        openCell(aroundPos);
                    }
                }
            }
        }
    }

    public int getClosedCellsCount(){
        return closedCellsCount;
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

    public Bomb getBomb(){
        return bomb;
    }

    public GameState getGameState(){
        return bomb.getGameState();
    }

    public int getPlacedFlagsCount(){
        return placedFlags;
    }

    public int getRemainingFlagsCount(){
        return remainingFlagsCount;
    }
}
