import GUI.MainFrame;
import debug.*;
import event.EventBus;
import logic.Game;

import javax.swing.*;



public class Main {
    private static final DebugCompoundLogger logger = new DebugCompoundLogger();
    private static final EventBus eventBus = new EventBus(logger);

    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame(logger);
            }
        });


        Game game = new Game(logger);
    }




}
