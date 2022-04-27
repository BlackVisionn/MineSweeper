package View;

import Model.*;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private Game game;
    private final int COLS = 9; // Столбцы
    private final int ROWS = 9; // Строки
    private final int BOMBS = 3;
    private final int HEALTH = 3;

    public GameFrame(){

        game = new Game(COLS, ROWS, BOMBS, HEALTH);
        game.startGame();
        GameFieldView mainBox = new GameFieldView(game);
        initFrame(mainBox); //Инициализация окна
    }

    private void initFrame(GameFieldView mainBox){

        setContentPane(mainBox);
        setResizable(false);
        setFocusable(true);
        setTitle("MineSweeper");
        setIconImage(getImage("icon"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private Image getImage(String name){
        String fileName = "/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }


}
