package view;

import controller.ModelListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;
import event.view.ViewEvent;
import event.view.ViewEventType;

import javax.swing.*;

/**
 * Created by user on 22-Oct-17.
 */
public class View
{
	private GameFrame gameFrame;
	private MenuFrame menuFrame;
	private ModelListener modelListener;

	public View(ModelListener modelListener)
	{
		this.modelListener=modelListener;

		showMenuFrame();

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

	private void showMenuFrame()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				menuFrame =new MenuFrame("Menu", modelListener);
			}
		});
	}

	public void startGame()
	{
		modelListener.handleEvent(new ViewEvent(this, ViewEventType.GAME_START_REQUEST, "AI"));
	}

}
