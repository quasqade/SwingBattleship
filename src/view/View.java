package view;

import controller.ModelListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;
import event.view.ViewEvent;
import event.view.ViewEventType;
import model.Cell;
import model.board.GameBoard;

import javax.swing.*;

/**
 * Created by user on 22-Oct-17.
 */

/*View can technically be considered a part of controller alongside listeners,
 *but can also be considered an compound representation of all true Views (Swing components).
 *It provides methods to influence Swing components to listeners*/
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
			gameFrame =new GameFrame("Battleship", modelListener);
	}

	private void showMenuFrame()
	{
			menuFrame =new MenuFrame("Menu", modelListener);
	}


	public void initializeGameFrame()
	{
		showGameFrame();
		while (gameFrame==null)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		menuFrame.dispose();
		gameFrame.revalidate();
		//gameFrame.debugConsoleLoop();
	}

	public void initializeBoard(GameBoard board)
	{
		gameFrame.initializeBoard(board);
	}

	public void drawHit(String symbol, Cell coordinates)
	{
		gameFrame.drawHit(symbol, coordinates);
	}

}
