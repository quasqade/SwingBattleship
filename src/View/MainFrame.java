package View;

import debug.Logger;
import debug.DebugMessage;
import debug.VerbosityLevel;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(String name)
    {
        super(name);
        Logger.push(new DebugMessage("Initialized View frame on thread " + Thread.currentThread() + "; EDT: " + SwingUtilities.isEventDispatchThread(), VerbosityLevel.IMPORTANT));
    }


}
