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
    // Проверка на победу
    void checkWinner(){

        if(gameState == GameState.PLAY || gameState == GameState.BOMBED){
            if(cell.getClosedCellsCount() == cell.getBomb().getTotalBombs() && cell.getBomb().getTotalBombs() == cell.getPlacedFlagsCount()){
                gameState = GameState.WIN;
            }
        }
    }

    public boolean isWin(){
        return gameState == GameState.WIN;
    }

    public GameState getGameState(){
        return gameState;
    }

    public Cell getCell(){
        return cell;
    }

    public GameField getGameField(){
        return gameField;
    }
}
