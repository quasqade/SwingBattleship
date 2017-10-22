package event.view;

import java.util.EventObject;

/**
 * Created by user on 22-Oct-17.
 */
public class ViewEvent extends EventObject
{
/**
 * Constructs a prototypical Event.
 *
 * @param source The object on which the Event initially occurred.
 * @throws IllegalArgumentException if source is null.
 */
public ViewEvent(Object source)
{
	super(source);
}
}
