package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFieldView extends JPanel {

    //private JPanel panel;
    private JLabel label;
    private final int IMG_SIZE = 50;
    private Game _game;
    private GameControl gameControl;


    public GameFieldView(Game game){
        _game = game;
        gameControl = new GameControl(_game);
        setImages();
        setLayout(new BorderLayout());
        label = new JLabel("Осталось жизней: " + _game.getCell().getBomb().getHealth() + " Осталось флагов: " + _game.getCell().getRemainingFlagsCount());
        add(label, BorderLayout.NORTH);

        add(new CellWidget(gameControl));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMG_SIZE;
                int y = e.getY() / IMG_SIZE;
                CellPosition cellPos = new CellPosition(x, y);
                if (e.getButton() == MouseEvent.BUTTON1){
                    gameControl.pressedLeftButton(cellPos);
                }
                if (e.getButton() == MouseEvent.BUTTON3){
                    gameControl.pressedRightButton(cellPos);
                }

                label.setText(message());
                repaint();

                if(_game.getGameState() != GameState.PLAY && _game.getGameState() != GameState.BOMBED){
                    JOptionPane.showMessageDialog(null, _game.isWin() ? "Победа" : "Поражение");
                    System.exit(0);
                }
            }
        });
    }

    private String message(){
        return "Осталось жизней: " + _game.getCell().getBomb().getHealth() + " Осталось флагов: " + _game.getCell().getRemainingFlagsCount();
    }

    private Image getImage(String name){
        String fileName = "/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    private void setImages(){
        for (CellState cellState : CellState.values()){
            cellState.image = getImage(cellState.name().toLowerCase());
        }
    }
}
