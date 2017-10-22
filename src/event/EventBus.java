package event;

import debug.DebugCompoundLogger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

public class EventBus {

    private DebugCompoundLogger logger;

    public EventBus(DebugCompoundLogger logger)
    {
        this.logger=logger;
        logger.debug(new DebugMessage("Created EventBus on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
    }
}
