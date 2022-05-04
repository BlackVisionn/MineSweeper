package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class CellWidget extends JPanel {

    private final int CELL_SIZE = 50;
    private Game _game;

    public CellWidget(Game game){

        setPreferredSize(new Dimension(Coord.getSize().x * CELL_SIZE, Coord.getSize().y * CELL_SIZE));
        _game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(CellPosition cellPos : Coord.getAllCoords()){
            g.drawImage((Image) _game.getCurrentCellState(cellPos).image, cellPos.x * CELL_SIZE, cellPos.y * CELL_SIZE, this);
        }
    }
}

