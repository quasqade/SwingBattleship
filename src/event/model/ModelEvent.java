package event.model;

import java.util.EventObject;

/**
 * Created by user on 22-Oct-17.
 */
public class ModelEvent extends EventObject {
    private ModelEventType prType;
    private String prMessage;
    private Object additionalObject;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ModelEvent(Object source) {
        super(source);
    }

    public ModelEvent(
            Object source,
            ModelEventType type,
            String message) {
        this(source, type, message, null);
    }

    public ModelEvent(
            Object source,
            ModelEventType type,
            String message, Object additionalObject) {
        this(source);
        this.prType = type;
        this.prMessage = message;
        this.additionalObject = additionalObject;
    }

    public ModelEventType type() {
        return prType;
    }

    public String message()
    {
        return prMessage;
    }

    public Object object()
    {
        return additionalObject;
    }
}
