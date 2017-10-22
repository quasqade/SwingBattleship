package view;

import controller.ModelListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

public class GameFrame extends JFrame {

	private ModelListener modelListener;
    public GameFrame(String name, ModelListener modelListener)
    {
        super(name);
        this.modelListener=modelListener;
        Logger.push(new DebugMessage("Initialized game frame " + this.getTitle() + " with listener " + modelListener.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
    }


}
