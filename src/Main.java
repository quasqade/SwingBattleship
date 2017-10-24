import controller.ModelListener;
import controller.ViewListener;
import view.View;
import model.Model;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;


public class Main {


    public static void main(String[] args)
    {
		ModelListener modelListener = new ModelListener();
		ViewListener viewListener = new ViewListener();

		Model model = new Model(viewListener);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    View view = new View(modelListener);
					viewListener.setView(view);
                }
            });
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}


		modelListener.setModel(model);


    }




}
