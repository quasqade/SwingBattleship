package view;

import controller.ModelListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

/**
 * Created by user on 22-Oct-17.
 */
public class View
{
	private GameFrame gameFrame;
	private ModelListener modelListener;

	public View(ModelListener modelListener)
	{
		this.modelListener=modelListener;

		showGameFrame();

		Logger.push(new DebugMessage("Initialized view instance " + this.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
		Logger.push(new DebugMessage("Associated view instance " + this.toString() + " with listener " + modelListener.toString(), VerbosityLevel.GENERAL));
	}

	private void showGameFrame()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gameFrame =new GameFrame("Battleship", modelListener);
			}
		});
	}
}
