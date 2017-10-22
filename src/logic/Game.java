package logic;

import debug.DebugCompoundLogger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

public class Game {
    private DebugCompoundLogger logger;
    public Game(DebugCompoundLogger logger)
    {
       this.logger = logger;
       logger.debug(new DebugMessage("Initialized game instance on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));;
    }
}
