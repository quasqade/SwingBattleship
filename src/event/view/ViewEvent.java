package event.view;

import java.util.EventObject;

/**
 * Created by user on 22-Oct-17.
 */
public class ViewEvent extends EventObject {
    private ViewEventType prType;
    private String prMessage;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ViewEvent(Object source) {
        super(source);
    }


    public ViewEvent(
            Object source,
            ViewEventType type,
            String message) {
        this(source);
        this.prType = type;
        this.prMessage = message;
    }

    public ViewEventType type() {
        return prType;
    }

    public String message()
    {
        return prMessage;
    }
}
