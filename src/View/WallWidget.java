package View;

import Model.CellPosition;

import java.awt.*;

public class WallWidget extends UnitWidget{

    private String name = "wall";

    public WallWidget(Graphics g, CellPosition cellPosition){
        super(g, cellPosition);

        setImage(name);

        paint(g);
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(img, cellPos.x * CELL_SIZE, cellPos.y * CELL_SIZE, null);
    }
}
