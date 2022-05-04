package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class CellWidget extends JPanel {

    private final int CELL_SIZE = 50;
    //private Game _game;
    private GameControl _gameControl;

    public CellWidget(GameControl gameControl){

        setPreferredSize(new Dimension(Coord.getSize().x * CELL_SIZE, Coord.getSize().y * CELL_SIZE));
        _gameControl = gameControl;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(CellPosition cellPos : Coord.getAllCoords()){
            g.drawImage((Image) _gameControl.getCurrentCellState(cellPos).image, cellPos.x * CELL_SIZE, cellPos.y * CELL_SIZE, this);
        }
    }
}

