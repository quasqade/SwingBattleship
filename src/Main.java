import Controller.ModelListener;
import Controller.ViewListener;
import View.View;
import Model.Game;


public class Main {


    public static void main(String[] args)
    {
		ModelListener modelListener = new ModelListener();
		ViewListener viewListener = new ViewListener();

		Game game = new Game();
		View view = new View();

		modelListener.registerObserver(viewListener);
		viewListener.registerObserver(modelListener);


    }




}
