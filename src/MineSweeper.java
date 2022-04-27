import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import sweeper.*;

public class MineSweeper extends JFrame {

    private Game game;

    private JPanel panel;
    private JLabel label;
    private final int COLS = 9; // Столбцы
    private final int ROWS = 9; // Строки
    private final int BOMBS = 3;
    private final int HEALTH = 3;
    private final int IMG_SIZE = 50;

    public static void main(String[] args) {

        new MineSweeper(); // Экземпляр нашей программы
    }

    //Всё необходимое для запуска окна
    private MineSweeper(){
        game = new Game(COLS, ROWS, BOMBS, HEALTH);
        game.startGame();
        setImages();
        initLabel();
        initPanel(); //Инициализация панели
        initFrame(); //Инициализация окна
    }

    private void initFrame(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setIconImage(getImage("icon"));
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void initLabel(){
        label = new JLabel("Осталось жизней:" + BOMBS + " Осталось флагов:" + BOMBS);
        add(label, BorderLayout.NORTH);
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

    private Image getImage(String name){
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    private void setImages(){
        for (CellState cellState : CellState.values()){
            cellState.image = getImage(cellState.name().toLowerCase());
        }
    }
}
