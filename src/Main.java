import controller.ModelListener;
import controller.ViewListener;
import view.View;
import model.Model;


public class Main {


    public static void main(String[] args)
    {
		ModelListener modelListener = new ModelListener();
		ViewListener viewListener = new ViewListener();

		Model model = new Model(viewListener);
		View view = new View(modelListener);

		modelListener.setModel(model);
		viewListener.setView(view);

		//view.startGame();


    }




}
