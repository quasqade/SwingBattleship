package debug;


public class DebugLogger {

    private final DebugLevel debugLevel;
    private final DebugTarget debugTarget;

    public DebugLogger(DebugLevel debugLevel, DebugTarget debugTarget)
    {
        this.debugLevel = debugLevel;
        this.debugTarget = debugTarget;
    }

    public void processMessage(DebugMessage msg)
    {
        int msgVerbosity = msg.level.ordinal();
        int dbgVerbosity = debugLevel.ordinal();

       if (dbgVerbosity>msgVerbosity)
       {
           outputToTarget(msg.message);
       }
    }

    private void outputToTarget(String message)
    {
        if (debugTarget==DebugTarget.STREAM_OUT)
        {
            System.out.println(message);
        }
    }
}
