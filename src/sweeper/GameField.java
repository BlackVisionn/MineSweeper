package sweeper;
public class GameField {
    private Cell bombCell;
    private int totalBombs;

    GameField (int totalBombs){
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    // Заполнение ячеек нижнего уровня
    void createField(){
        bombCell = new Cell(CellState.ZERO);
        for (int i = 0; i<totalBombs; i++){
            placeBomb();
        }
    }

    // Размещение мин на ячейки нижнего уровня
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
            if (CellState.BOMB != bombCell.getCellState(aroundPos)){
                bombCell.setCellState(aroundPos, bombCell.getCellState(aroundPos).getNextNumber());
            }
        }
    }

    CellState getBombCellState (CellPosition cellPos){

        return bombCell.getCellState(cellPos);
    }

    public int getTotalBombs() {

        return totalBombs;
    }

    public Cell getBombCell() {

        return bombCell;
    }
}
