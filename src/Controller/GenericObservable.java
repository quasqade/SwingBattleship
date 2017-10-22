package Controller;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * Created by user on 22-Oct-17.
 */
public class GenericObservable implements Observable
{
	private List<Observer> observers;


@Override
public void registerObserver(Observer observer)
{
	if (observers==null)
	{
		observers = new ArrayList<>();
	}
	observers.add(observer);
}

private void fireEvent(EventObject ev)
{
	if (observers!=null)
	{
		for (Observer observer: observers
			 )
		{
			observer.handleEvent(ev);
		}
	}
}
}
