package Model;
import java.util.Random;

public class GameField {
    private Cell bombCell;
    private int totalBombs;
    private int wallsCount;
    private boolean direction;

    GameField (int totalBombs, int walls){
        this.totalBombs = totalBombs;
        wallsCount = walls;
        fixBombsCount();
    }

    // Заполнение ячеек нижнего уровня
    void createField(){
        bombCell = new Cell(CellState.ZERO);
        for (int i = 0; i<wallsCount; i++){
            placeWall();
        }
        for (int i = 0; i<totalBombs; i++){
            placeBomb();
        }
    }

    // Размещение мин на ячейки нижнего уровня
    private void placeBomb(){
        while (true){
            CellPosition cellPos = Coord.getRandomCellPos();
            if(bombCell.getCellState(cellPos) == CellState.BOMB || bombCell.getCellState(cellPos) == CellState.WALL){
                continue;
            }
            bombCell.setCellState(cellPos, CellState.BOMB);
            incNumbersAroundBomb(cellPos);
            break;
        }
    }

    // Размещение мин на ячейки нижнего уровня
    private void placeWall(){
        Random random = new Random();
        while (true){
            CellPosition cellPos = Coord.getRandomCellPos();
            if(bombCell.getCellState(cellPos) == CellState.BOMB || bombCell.getCellState(cellPos) == CellState.WALL){
                continue;
            }
            bombCell.setCellState(cellPos, CellState.WALL);
            direction = random.nextBoolean();
            walling(cellPos, direction);
            break;
        }
    }

    // Установка стены на поле
    private void walling(CellPosition cellPos, boolean direction){
        for (CellPosition wallPos : Coord.getWallCellPositions(cellPos, direction)){
            if (bombCell.getCellState(wallPos) != CellState.BOMB){
                bombCell.setCellState(wallPos, CellState.WALL);
            }
        }
        for (CellPosition blockedPos : Coord.getBlockedCellPositions(cellPos, direction)){
            if (bombCell.getCellState(blockedPos) != CellState.BOMB){
                bombCell.setCellState(blockedPos, CellState.BLOCKED);
            }
        }
    }

    // Фиксация максимального числа бомб на поле
    private void fixBombsCount(){
        int maxBombs = Coord.getSize().x * Coord.getSize().y / 2;
        if(totalBombs > maxBombs){
            totalBombs = maxBombs;
        }
    }

    // Установка цифр вокруг мины
    private void incNumbersAroundBomb(CellPosition cellPos){
        for (CellPosition aroundPos : Coord.getCellPositionsAround(cellPos)){
            if (bombCell.getCellState(aroundPos) != CellState.BOMB && bombCell.getCellState(aroundPos) != CellState.WALL && bombCell.getCellState(aroundPos) != CellState.BLOCKED){
                bombCell.setCellState(aroundPos, bombCell.getCellState(aroundPos).getNextNumber());
            }
        }
    }

    CellState getBombCellState (CellPosition cellPos){

        return bombCell.getCellState(cellPos);
    }

    boolean getDirection(){
        return direction;
    }

    public int getTotalBombs() {

        return totalBombs;
    }

    public Cell getBombCell() {

        return bombCell;
    }
}
