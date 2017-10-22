package controller;

import view.*;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;

import java.util.EventObject;

/**
 * Created by user on 22-Oct-17.
 */
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

}
}
