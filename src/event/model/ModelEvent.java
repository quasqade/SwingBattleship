package event.model;

import java.util.EventObject;

/**
 * Created by user on 22-Oct-17.
 */
public class ModelEvent extends EventObject
{
/**
 * Constructs a prototypical Event.
 *
 * @param source The object on which the Event initially occurred.
 * @throws IllegalArgumentException if source is null.
 */
public ModelEvent(Object source)
{
	super(source);
}
}
