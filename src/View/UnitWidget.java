package View;

import Model.CellPosition;

import javax.swing.*;
import java.awt.*;

public class UnitWidget extends JPanel {
    protected CellPosition cellPos;
    protected int CELL_SIZE;
    protected Image img;

    public UnitWidget(Graphics g, CellPosition cellPosition){
        cellPos = cellPosition;
        CELL_SIZE = 50;
    }

    public void setImage(String name){

        String imgPath = "/img/" + name + ".png";
        img = new ImageIcon(getClass().getResource(imgPath)).getImage();
    }

    @Override
    public void paint(Graphics g) {
        if (img == null) { return; }
    }
}
