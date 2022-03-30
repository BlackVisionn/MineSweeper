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

        return gameField.get(cellPos);
    }
}
