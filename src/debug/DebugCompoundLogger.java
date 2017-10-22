package debug;

import java.util.ArrayList;
import java.util.List;

public class DebugCompoundLogger {
    private List<DebugLogger> attachedLoggers;

    public DebugCompoundLogger()
    {
        attachedLoggers = new ArrayList<>();
        attachedLoggers.add(new DebugLogger(DebugLevel.ALL, DebugTarget.STREAM_OUT));
    }

    public synchronized void debug(DebugMessage msg)
    {
        for (DebugLogger logger: attachedLoggers
             ) {
            logger.processMessage(msg);
        }
    }
}
