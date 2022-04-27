package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFieldView extends JPanel {

    private JPanel panel;
    private JLabel label;
    private final int IMG_SIZE = 50;
    private Game game;

    public GameFieldView(Game game){
        this.game = game;
        setImages();
        initLabel();
        initPanel(); //Инициализация панели
    }

    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                for(CellPosition cellPos : Coord.getAllCoords()){
                    g.drawImage((Image) game.getCurrentCellState(cellPos).image, cellPos.x * IMG_SIZE, cellPos.y * IMG_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMG_SIZE;
                int y = e.getY() / IMG_SIZE;
                CellPosition cellPos = new CellPosition(x, y);
                if (e.getButton() == MouseEvent.BUTTON1){
                    game.pressedLeftButton(cellPos);
                }
                if (e.getButton() == MouseEvent.BUTTON3){
                    game.pressedRightButton(cellPos);
                }

                label.setText(message());
                panel.repaint();

                if(game.getGameState() != GameState.PLAY && game.getGameState() != GameState.BOMBED){
                    JOptionPane.showMessageDialog(null, game.isWin() ? "Победа" : "Поражение");
                    System.exit(0);
                }
            }
        });

        panel.setPreferredSize(new Dimension(Coord.getSize().x * IMG_SIZE,Coord.getSize().y * IMG_SIZE));
        add(panel);
    }

    private String message(){
        return "Осталось жизней: " + game.getCell().getBomb().getHealth() + " Осталось флагов: " + game.getCell().getRemainingFlagsCount();
    }

    private void initLabel(){
        label = new JLabel("Осталось жизней: " + game.getCell().getBomb().getHealth() + " Осталось флагов: " + game.getCell().getRemainingFlagsCount());
        add (label, BorderLayout.NORTH);
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
