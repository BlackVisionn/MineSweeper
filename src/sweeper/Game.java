package sweeper;

public class Game {

    private GameField gameField;
    private GameState gameState;

    public GameState getGameState(){

        return gameState;
    }

    public Game (int cols, int rows, int bombs){
        Coord.setSize(new CellPosition(cols, rows));
        gameField = new GameField(bombs);
    }

    public void startGame(){
        gameField.createField();
        gameState = GameState.PLAY;
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

        gameField.openCell(cellPos);
        checkWinner();
    }

    public void pressedRightButton(CellPosition cellPos) {
        gameField.setFlagToCell(cellPos);
    }

    private void checkWinner(){

        if(gameState == GameState.PLAY){
            if(gameField.getClosedCellsCount() == gameField.getTotalBombs()){
                gameState = GameState.WIN;
            }
        }
    }
}
