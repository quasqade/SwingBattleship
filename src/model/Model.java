package model;

import controller.ViewListener;
import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;
import event.model.ModelEvent;
import event.model.ModelEventType;
import model.enemy.Enemy;
import model.board.GameBoard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Model
{
	private ViewListener viewListener;
	private GameBoard enemyBoard;
	private Enemy enemy;

    public Model(ViewListener viewListener)
    {
    	this.viewListener = viewListener;
        Logger.push(new DebugMessage("Initialized model instance " + this.toString() + " on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
        Logger.push(new DebugMessage("Associated model instance " + this.toString() + " with listener " + viewListener.toString(), VerbosityLevel.GENERAL));
    }

    public void initializeGame(Enemy enemy)
    {
        this.enemy = enemy;
        enemyBoard = new GameBoard(10, 10);
        enemy.populateBoard(enemyBoard);
        enemyBoard.printBoard();
        viewListener.handleEvent(new ModelEvent(this, ModelEventType.GAME_CREATED, "Game created"));
    }

    //TODO: abort gracefully
    public void abort()
    {
        System.exit(0);
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }

    public void processHit(int x, int y) {
        //TODO determine why first hit sometimes comes before board is properly updated, requiring the following line
        enemyBoard.updateBoard();
        String[][] oldBoardArray = enemyBoard.getSymbolArray(); //this board represents last state of a board before update to be processed into list of HitResults to send over the network/eventbus
        enemy.processHit(x, y);
        List<HitResult> results = getDiffBetweenBoards(enemyBoard.getSymbolArray(), oldBoardArray);
        for (HitResult result: results
             )
        {
            viewListener.handleEvent(new ModelEvent(this, ModelEventType.HIT_RESULT, result.result, result.coords));
        }
        if (results.size()==1)
        {
            if (results.get(0).result.equals(" . "))
            {
                enemy.makeHit();
            }
        }
    }

    public void processBoardRequest()
    {
        GameBoard obscuredBoard = new GameBoard(enemyBoard);
        obscuredBoard.setShips(new HashSet<>());
        obscuredBoard.updateBoard();
        viewListener.handleEvent(new ModelEvent(this, ModelEventType.BOARD, "Response to initial board request", obscuredBoard));
    }

    private List<HitResult> getDiffBetweenBoards(String[][] newBoard, String[][] oldBoard)
    {
        List<HitResult> output = new ArrayList<>();
        for(int i = 0; i<newBoard.length; i++)
        {
            for (int j = 0; j < newBoard[0].length; j++)
            {
                if (!newBoard[i][j].equals(oldBoard[i][j]))
                {
                    String result = newBoard[i][j];
                    if (result.charAt(2) == 'f')
                        result = "x";
                    output.add(new HitResult(result, new Cell(i,j)));
                }
            }
        }
        return output;
    }
}
