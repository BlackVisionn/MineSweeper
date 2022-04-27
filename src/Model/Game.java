package Model;

public class Game {

    private GameField gameField;
    private GameState gameState;
    private Cell cell;
    private int health;


    public Game (int cols, int rows, int bombs, int health){
        Coord.setSize(new CellPosition(cols, rows));
        this.health = health;
        gameField = new GameField(bombs);
        cell = new Cell(CellState.CLOSED);
    }

    // Начало игры
    public void startGame(){
        gameState = GameState.PLAY;
        gameField.createField();
        cell.getCells(gameField, cell, health);
    }

    // Получить состояние ячейки нужного уровня
    public CellState getCurrentCellState(CellPosition cellPos){

        if(cell.getCellState(cellPos) == CellState.OPENED){
            return gameField.getBombCellState(cellPos);
        }
        else {
            return cell.getCellState(cellPos);
        }
    }

    public void pressedLeftButton(CellPosition cellPos) {
        cell.openCell(cellPos);
        gameState = cell.getGameState();
    }

    public void pressedRightButton(CellPosition cellPos) {
        cell.setFlagToCell(cellPos);
        checkWinner();
    }

    public GameState getGameState(){
        return gameState;
    }

    public Cell getCell(){
        return cell;
    }

    // Проверка на победу
    private void checkWinner(){

        if(gameState == GameState.PLAY || gameState == GameState.BOMBED){
            if(cell.getClosedCellsCount() == cell.getBomb().getTotalBombs() && cell.getBomb().getTotalBombs() == cell.getPlacedFlagsCount()){
                gameState = GameState.WIN;
            }
        }
    }

    public boolean isWin(){
        return gameState == GameState.WIN;
    }
}
