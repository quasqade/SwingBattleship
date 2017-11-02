package controller;

import java.util.EventObject;

import event.view.ViewEvent;
import model.*;
import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import model.enemy.RandomAIEnemy;

/**
 * Created by user on 22-Oct-17.
 */

/*A listener that attaches to a model, acting as a one-way view->model connection*/
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
                        model.initializeGame(new RandomAIEnemy());
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
                case FIRE:
                    {
                        String command = ((ViewEvent) ev).message();
                        String[] coords = command.split(" ");
                        model.processHit(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                    }
                    break;
                case UPDATE:
                    {
                        model.getEnemyBoard().printBoard();
                    }
                case BOARD_REQUEST:
                    {
                        model.processBoardRequest();
                    }
            }

        }
    }


}
