package view;

import controller.ModelListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;
import event.view.ViewEvent;
import event.view.ViewEventType;
import model.board.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*A main frame where gameplay happens*/
public class GameFrame extends JFrame {

	private ModelListener modelListener;
    public GameFrame(String name, ModelListener modelListener)
    {
        super(name);
        this.modelListener=modelListener;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (JOptionPane.showConfirmDialog(GameFrame.this, "Are you sure you want to quit?", "Really quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
                {
                    modelListener.handleEvent(new ViewEvent(this, ViewEventType.ABORT, "Window cross"));
                }
            }
        });
        setMinimumSize(new Dimension(640, 320));
        setLayout(new BorderLayout());
        add(new JPanel(), BorderLayout.CENTER);
        pack();
        setVisible(true);
        Logger.push(new DebugMessage("Initialized game frame " + this.getTitle() + " with listener " + modelListener.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
    }


    public void setEnemyBoard(GameBoard enemyBoard)
    {
        return;
    }


    public void debugConsoleLoop()
    {
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.toUpperCase().equals("PRINT"))
            {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        modelListener.handleEvent(new ViewEvent(this, ViewEventType.UPDATE, "ConsoleUpdate"));
                    }
                });
                continue;
            }
            String[] input = command.split(" ");
            try
            {
                {
                    if (input.length != 3)
                        throw new Exception("ComEx");
                    if (input[0].toUpperCase().equals("FIRE")) {
                        int x = Integer.parseInt(input[1]);
                        int y = Integer.parseInt(input[2]);
                        ExecutorService executor = Executors.newFixedThreadPool(1);
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                modelListener.handleEvent(new ViewEvent(this, ViewEventType.FIRE, x + " " + y));
                            }
                        });
                        continue;
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println("Wrong command syntax");
                continue;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Logger.push(new DebugMessage(e.getMessage(), VerbosityLevel.WARNING));
                e.printStackTrace();
            }
        }
    }

}
