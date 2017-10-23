package model;

import controller.ViewListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;
import model.board.BoardProvider;
import model.board.GameBoard;
import model.enemy.Enemy;

import javax.swing.*;

public class Model
{
	private ViewListener viewListener;
	private GameBoard enemyBoard;
	private BoardProvider provider;
	private Enemy enemy;

    public Model(ViewListener viewListener)
    {
    	this.viewListener = viewListener;
        Logger.push(new DebugMessage("Initialized model instance " + this.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
        Logger.push(new DebugMessage("Associated model instance " + this.toString() + " with listener " + viewListener.toString(), VerbosityLevel.GENERAL));
    }

    public void initializeGame(BoardProvider provider, Enemy enemy)
    {
        this.provider=provider;
        this.enemy=enemy;
        enemyBoard = new GameBoard(10, 10);
        provider.populateBoard(enemyBoard);
        enemyBoard.printBoard();
    }

    //TODO: abort gracefully
    public void abort()
    {
        System.exit(0);
    }
}
