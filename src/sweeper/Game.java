package sweeper;

public class Game {

    private GameField gameField;


    public Game (int cols, int rows, int bombs){
        Coord.setSize(new CellPosition(cols, rows));
        gameField = new GameField(bombs);
    }

    public void startGame(){
        gameField.createField();
    }

    public CellState getCell(CellPosition cellPos){

        if(gameField.getClosedCellState(cellPos) == CellState.OPENED){
            return gameField.getBombCellState(cellPos);
        }
        else {
            return gameField.getClosedCellState(cellPos);
        }

    }

    public void pressedLeftButton(CellPosition cellPos) {

        gameField.setOpenedToCell(cellPos);
    }

    public void pressedRightButton(CellPosition cellPos) {
        gameField.setFlagToCell(cellPos);
    }
}
