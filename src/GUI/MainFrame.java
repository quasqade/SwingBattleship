package GUI;

import debug.DebugCompoundLogger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

public class MainFrame extends JFrame {
    private DebugCompoundLogger logger;

    public MainFrame(DebugCompoundLogger logger)
    {
        this.logger=logger;
        logger.debug(new DebugMessage("Initialized GUI frame on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
    }


}
