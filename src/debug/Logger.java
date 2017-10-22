package debug;

import java.util.ArrayList;
import java.util.List;

public class Logger
{
    private static List<DebugLoggerComponent> attachedLoggers;
    private static boolean initialized=false;

    public Logger()
    {
		if (!initialized)
		{
			initialize();
		}
    }

    private static void initialize()
	{
		initialized=true;
		attachedLoggers = new ArrayList<>();
		attachedLoggers.add(new DebugLoggerComponent(DebugLevel.ALL, DebugTarget.STREAM_OUT));
	}

    public static synchronized void push(DebugMessage msg)
    {
    	if (!initialized)
    		initialize();

        for (DebugLoggerComponent logger: attachedLoggers
                ) {
            logger.processMessage(msg);
        }
    }

}
