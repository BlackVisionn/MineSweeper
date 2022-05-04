package Model;

import java.awt.*;

public class GameControl {

    private Game _game;

    public GameControl(Game game){
        _game = game;
    }

    public void pressedLeftButton(CellPosition cellPos) {
        _game.getCell().openCell(cellPos);
        _game.getCell().getGameState();
        _game.checkWinner();
    }

    public void pressedRightButton(CellPosition cellPos) {
        _game.getCell().setFlagToCell(cellPos);
        _game.checkWinner();
    }

    // Получить состояние ячейки нужного уровня
    public CellState getCurrentCellState(CellPosition cellPos){

        if(_game.getCell().getCellState(cellPos) == CellState.OPENED){
            return _game.getGameField().getBombCellState(cellPos);
        }
        else {
            return _game.getCell().getCellState(cellPos);
        }
    }


}
