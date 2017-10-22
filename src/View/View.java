package View;

import Controller.ModelListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

/**
 * Created by user on 22-Oct-17.
 */
public class View
{
	private MainFrame mainFrame;
	private ModelListener modelListener;

	public View(ModelListener modelListener)
	{
		this.modelListener=modelListener;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				mainFrame=new MainFrame("Battleship");
			}
		});

		Logger.push(new DebugMessage("Initialized view instance on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
	}

}
