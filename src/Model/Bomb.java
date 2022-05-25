package Model;

public class Bomb {
    private int health;
    private int totalBombs;
    private Cell closedCell;
    private Cell bombCell;
    private GameState gameState;

    public Bomb(int totalBombs, Cell closedCell, Cell bombCell, int health){
        this.totalBombs = totalBombs;
        this.closedCell = closedCell;
        this.bombCell = bombCell;
        this.health = health;
        gameState = GameState.PLAY;
    }

    public int explose(CellPosition cellPos, int closedCellsCount){

        health--;
        totalBombs--;
        closedCellsCount--;
        if (health > 0){
            gameState = GameState.BOMBED;
            closedCell.setCellState(cellPos, CellState.BOMBED);
        }
        else {
            gameState = GameState.LOSE;
            closedCell.setCellState(cellPos, CellState.BOMBED);
            for (CellPosition otherPos : Coord.getAllCoords()){
                // Открыть ячейку с закрытой бомбой
                if(closedCell.getCellState(otherPos) == CellState.CLOSED && bombCell.getCellState(otherPos) == CellState.BOMB){
                    closedCell.setCellState(otherPos,CellState.OPENED);
                }

                else {
                    // поставить нету бомбы на ячейке с флажком
                    if (closedCell.getCellState(otherPos) == CellState.FLAGED){
                        closedCell.setCellState(otherPos, CellState.NOBOMB);
                    }
                }
            }
        }
        return closedCellsCount;
    }

    public GameState getGameState(){
        return gameState;
    }

    public int getTotalBombs(){
        return totalBombs;
    }

    public int getHealth(){
        return health;
    }
}
