package controller;

import event.model.ModelEvent;
import event.view.ViewEvent;
import model.Cell;
import model.Model;
import model.board.GameBoard;
import view.*;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;

import javax.swing.*;
import java.util.EventObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 22-Oct-17.
 */

/*A listener that attaches to a model, acting as a one-way model->view connection*/
public class ViewListener
{
public View getView()
{
	return view;
}

public void setView(View view)
{
	this.view = view;
	Logger.push(new DebugMessage("Associated view " + view.toString() + " with listener " + this.toString(), VerbosityLevel.GENERAL ));
}

private View view;

public void handleEvent(EventObject ev)
{
if (ev instanceof ModelEvent)
{
	switch (((ModelEvent) ev).type())
	{
		case GAME_CREATED:
		{
			switch (((ModelEvent) ev).message())
			{
				case "Game created":
				{
					ExecutorService executor = Executors.newSingleThreadExecutor();
					executor.execute(new Runnable() {
						@Override
						public void run() {
							view.initializeGameFrame();
						}
					});


					break;
				}
			}
			break;
		}
		case BOARD:
		{
			view.initializeBoard((GameBoard) ((ModelEvent) ev).object());
			break;
		}

		case HIT_RESULT:
		{
			view.drawHit(((ModelEvent) ev).message(), (Cell)((ModelEvent) ev).object());
		}
	}
}
}
}
