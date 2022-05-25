package Tests;

import Model.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    private Cell closedCell;
    private Cell bombCell;
    private CellPosition cellPosition;

    @Test
    public void PlaceFlagToCell() {

        Coord.setSize(new CellPosition(4, 4));
        Game game = new Game(1,1,0);
        game.startGame();
        closedCell = game.getCell();

        closedCell.setFlagToCell(new CellPosition(1,1));

        CellState result = closedCell.getCellState(new CellPosition(1,1));
        CellState expectedResult = CellState.FLAGED;

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void RemoveFlagFromCell() {

        Coord.setSize(new CellPosition(4, 4));
        Game game = new Game(1,1,0);
        game.startGame();
        closedCell = game.getCell();

        closedCell.setFlagToCell(new CellPosition(1,1));
        closedCell.setFlagToCell(new CellPosition(1,1));


        CellState result = closedCell.getCellState(new CellPosition(1,1));
        CellState expectedResult = CellState.CLOSED;

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void OpenBlockedCell() {

        Coord.setSize(new CellPosition(4, 4));
        Game game = new Game(1,1,1);
        game.startGame();
        closedCell = game.getCell();
        bombCell = game.getGameField().getBombCell();

        // Найдём бомбу и откроем её
        for (CellPosition cellPos : Coord.getAllCoords()){
            if(bombCell.getCellState(cellPos) == CellState.BLOCKED){
                closedCell.setOpenedToCell(cellPos);
                cellPosition = cellPos;
                break;
            }
        }

        CellState result = closedCell.getCellState(cellPosition);
        CellState expectedResult = CellState.OPENED;

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void OpenBombCell() {

        Coord.setSize(new CellPosition(4, 4));
        Game game = new Game(1,2,1);
        game.startGame();
        closedCell = game.getCell();
        bombCell = game.getGameField().getBombCell();
        Bomb bomb = new Bomb(game.getGameField().getTotalBombs(), closedCell, bombCell, 2);

        // Найдём бомбу и откроем её
        for (CellPosition cellPos : Coord.getAllCoords()){
            if(bombCell.getCellState(cellPos) == CellState.BOMB){
                bomb.explose(cellPos, Coord.getSize().x * Coord.getSize().y);
                cellPosition = cellPos;
                break;
            }
        }

        CellState result = closedCell.getCellState(cellPosition);
        CellState expectedResult = CellState.BOMBED;
        int healthResult = bomb.getHealth();
        int expectedHealthResult = 1;

        Assert.assertEquals(expectedResult, result);
        Assert.assertEquals(expectedHealthResult, healthResult);
    }

}