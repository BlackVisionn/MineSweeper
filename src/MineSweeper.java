import javax.swing.*;
import View.GameFrame;

public class MineSweeper {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame mainWindow = new GameFrame();
                mainWindow.setVisible(true);
            }
        });
    }
}
