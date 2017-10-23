package view;

import controller.ModelListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;
import event.view.ViewEvent;
import event.view.ViewEventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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


}
