package view;

import controller.ModelListener;
import debug.DebugLevel;
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

	private JPanel mainPanel;
	private JPanel friendlyBoardPanel;
	private JPanel enemyBoardPanel;
	private JPanel scorePanel;
	private JPanel controlPanel;


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
                    Logger.push(new DebugMessage("Sending abort event", VerbosityLevel.GENERAL));
                    modelListener.handleEvent(new ViewEvent(this, ViewEventType.ABORT, "Window cross"));
                }
            }
        });
        setMinimumSize(new Dimension(640, 320));
        this.mainPanel = new JPanel();
        add(mainPanel, BorderLayout.CENTER);
        layoutComponents();
        pack();
        repaint();
        setVisible(true);
        Logger.push(new DebugMessage("Initialized game frame " + this.getTitle() + " with listener " + modelListener.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
        modelListener.handleEvent(new ViewEvent(this, ViewEventType.BOARD_REQUEST, "Initial board request"));
    }



    private void layoutComponents()
    {
        if (mainPanel==null)
        {
            Logger.push(new DebugMessage("Attempted to layout components before main panel is created! Something is very wrong",
                                         VerbosityLevel.CRITICAL));
            return;
        }

        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        gbc.gridy=0;
        gbc.gridx=0;
        gbc.anchor=GridBagConstraints.FIRST_LINE_START;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);

        //First column
        gbc.weightx = 100;
        friendlyBoardPanel = new JPanel();
        layoutBoard(friendlyBoardPanel);
        mainPanel.add(friendlyBoardPanel, gbc);

        //Second column
        gbc.gridx++;
        gbc.weightx=30;
        scorePanel = new JPanel();
        layoutScore(scorePanel);
        mainPanel.add(scorePanel, gbc);

        //Third column
        gbc.gridx++;
        gbc.weightx=100;
        enemyBoardPanel = new JPanel();
        layoutBoard(enemyBoardPanel);
        mainPanel.add(enemyBoardPanel, gbc);

    }

    private void layoutBoard(JPanel boardPanel)
    {
        boardPanel.setBorder(BorderFactory.createEtchedBorder());
        boardPanel.add(new JLabel("AAAAAAAAAA"));
    }

    private void layoutScore(JPanel scorePanel)
    {

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

    public void initializeBoard(GameBoard board)
    {
        if (enemyBoardPanel==null)
        {
            Logger.push(new DebugMessage("Attempted to initialize board before frame is initalized!", VerbosityLevel.CRITICAL));
            return;
        }


        for(int i = 0; i < board.getX(); i++)
        {
            for (int j = 0; j < board.getY(); j++)
            {
                //TODO
            }
        }
    }
}
