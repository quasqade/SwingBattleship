package controller;

import java.util.EventObject;
import model.*;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;

/**
 * Created by user on 22-Oct-17.
 */
public class ModelListener
{
public Model getModel()
{
	return model;
}

public void setModel(Model model)
{
	this.model = model;
	Logger.push(new DebugMessage("Associated model " + model.toString() + " with listener " + this.toString(), VerbosityLevel.GENERAL ));
}

private Model model;
public void handleEvent(EventObject ev)
{

}
}
