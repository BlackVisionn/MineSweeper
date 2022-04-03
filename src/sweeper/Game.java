package sweeper;

public class Game {

    private GameField gameField;
    private GameState gameState;

    public GameState getGameState(){

        return gameState;
    }

    public Game (int cols, int rows, int bombs, int health){
        Coord.setSize(new CellPosition(cols, rows));
        gameField = new GameField(bombs, health);
    }

    public void startGame(){
        gameState = GameState.PLAY;
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

        if (gameOver()) return;
        gameField.openCell(cellPos);
        gameState = gameField.getGameState();

    }

    public void pressedRightButton(CellPosition cellPos) {

        if (gameOver()) return;
        gameField.setFlagToCell(cellPos);
        checkWinner();
    }

    private void checkWinner(){

        if(gameState == GameState.PLAY){
            if(gameField.getClosedCellsCount() == gameField.getTotalBombs() && gameField.getTotalBombs() == gameField.getPlacedFlagsCount()){
                gameState = GameState.WIN;
                System.out.print("Победа!");
            }
        }
        if(gameState == GameState.BOMBED){
            System.out.print("Проиграл!");
        }
    }
    private boolean gameOver(){
        if (gameState == GameState.PLAY || gameState == GameState.BOMBED){
            return false;
        }
        startGame();
        return true;
    }

    public boolean isWin(){
        return gameState == GameState.WIN;
    }
}
