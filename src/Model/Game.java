package Model;

import Controller.ViewListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

public class Game {
	private ViewListener viewListener;
    public Game(ViewListener viewListener)
    {
    	this.viewListener = viewListener;
        Logger.push(new DebugMessage("Initialized game instance on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
    }
}
