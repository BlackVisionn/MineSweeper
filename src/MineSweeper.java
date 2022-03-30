import javax.swing.*;
import java.awt.*;

import sweeper.Cell;
import sweeper.CellPosition;
import sweeper.Coord;
import sweeper.GameIcons;

public class MineSweeper extends JFrame {

    private JPanel panel;
    private final int COLS = 15; // Столбцы
    private final int ROWS = 15; // Строки
    private final int IMG_SIZE = 50;

    public static void main(String[] args) {

        new MineSweeper(); // Экземпляр нашей программы
    }

    //Всё необходимое для запуска окна
    private MineSweeper(){
        Coord.setSize(new CellPosition(COLS, ROWS));
        setImages();
        initPanel(); //Инициализация панели
        initFrame(); //Инициализация окна
    }

    private void initFrame(){
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(CellPosition cellPos : Coord.getAllCoords()){
                    g.drawImage((Image) GameIcons.FLAGED.image, cellPos.x * IMG_SIZE, cellPos.y * IMG_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(Coord.getSize().x * IMG_SIZE,Coord.getSize().y * IMG_SIZE));
        add(panel);
    }

    private Image getImage(String name){
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    private void setImages(){
        for (GameIcons gameIcon : GameIcons.values()){
            gameIcon.image = getImage(gameIcon.name().toLowerCase());
        }
    }
}
