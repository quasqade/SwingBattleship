package controller;

import java.util.EventObject;

import event.model.ModelEvent;
import event.view.ViewEvent;
import event.view.ViewEventType;
import model.*;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import model.board.AIBoardProvider;
import model.enemy.AIEnemy;
import model.enemy.Enemy;

/**
 * Created by user on 22-Oct-17.
 */
public class ModelListener {
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        Logger.push(new DebugMessage("Associated model " + model.toString() + " with listener " + this.toString(),
                                     VerbosityLevel.GENERAL));
    }

    private Model model;

    public void handleEvent(EventObject ev) {
        if (ev instanceof ViewEvent) {
            switch(((ViewEvent) ev).type()) {
                case GAME_START_REQUEST:
                    if (((ViewEvent) ev).message().equals("AI"))
                    {
                        model.initializeGame(new AIBoardProvider(), new AIEnemy());
                    }
                    break;

                case ABORT:
                    switch (((ViewEvent) ev).message())
                    {
                        case "Window cross":
                            model.abort();
                            break;
                        case "Exit button":
                            model.abort();
                            break;
                    }
                    break;
            }

        }
    }


}
