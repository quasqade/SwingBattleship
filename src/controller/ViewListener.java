package controller;

import event.model.ModelEvent;
import event.view.ViewEvent;
import model.Model;
import model.board.GameBoard;
import view.*;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;

import java.util.EventObject;

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
					view.initializeGameFrame();

					break;
				}
			}
			break;
		}
		case BOARD:
		{
			view.initializeBoard((GameBoard) ((ModelEvent) ev).object());
		}
	}
}
}
}
