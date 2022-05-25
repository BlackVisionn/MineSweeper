package Tests;

import Model.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Cell bombCell;
    @Test
    public void LoseGame() {

        Coord.setSize(new CellPosition(9, 9));
        Game game = new Game(10,1,1);
        GameControl gameControl = new GameControl(game);
        game.startGame();
        bombCell = game.getGameField().getBombCell();

        // Найдём бомбу и откроем её
        for (CellPosition cellPos : Coord.getAllCoords()){
            if(bombCell.getCellState(cellPos) == CellState.BOMB){
                gameControl.pressedLeftButton(cellPos);
            }
        }

        GameState result = game.getGameState();
        GameState expectedResult = GameState.LOSE;

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    //взорвались но игра продолжается
    public void PlayerExplodedButPlaying() {

        Coord.setSize(new CellPosition(4, 4));
        Game game = new Game(1,2,1);
        GameControl gameControl = new GameControl(game);
        game.startGame();
        bombCell = game.getGameField().getBombCell();

        // Найдём бомбу и откроем её
        for (CellPosition cellPos : Coord.getAllCoords()){
            if(bombCell.getCellState(cellPos) == CellState.BOMB){
                gameControl.pressedLeftButton(cellPos);
            }
        }

        GameState result = game.getGameState();
        GameState expectedResult = GameState.BOMBED;

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void WinGame() {

        Coord.setSize(new CellPosition(4, 4));
        Game game = new Game(1,1,1);
        GameControl gameControl = new GameControl(game);
        game.startGame();
        bombCell = game.getGameField().getBombCell();

        for (CellPosition cellPos : Coord.getAllCoords()){
            if(bombCell.getCellState(cellPos) != CellState.BOMB){
                gameControl.pressedLeftButton(cellPos);
            }
            else if(bombCell.getCellState(cellPos) == CellState.BOMB){
                gameControl.pressedRightButton(cellPos);
            }
        }

        GameState result = game.getGameState();
        GameState expectedResult = GameState.WIN;

        Assert.assertEquals(expectedResult, result);
    }
}