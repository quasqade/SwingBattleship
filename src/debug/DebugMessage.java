package debug;

public class DebugMessage {
    public final String message;
    public final VerbosityLevel level;

    public DebugMessage(String message, VerbosityLevel level)
    {
        this.message=message;
        this.level=level;
    }
}
