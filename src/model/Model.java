package model;

import controller.ViewListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

public class Model
{
	private ViewListener viewListener;
    public Model(ViewListener viewListener)
    {
    	this.viewListener = viewListener;
        Logger.push(new DebugMessage("Initialized model instance " + this.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
        Logger.push(new DebugMessage("Associated model instance " + this.toString() + " with listener " + viewListener.toString(), VerbosityLevel.GENERAL));
        new GameBoard(10, 10);
    }
}
