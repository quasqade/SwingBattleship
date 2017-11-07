package model.enemy;

import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import model.Ship;
import model.ShipType;
import model.board.GameBoard;

import java.util.HashSet;
import java.util.Random;


/*This class represents a random AI enemy that places ships at random
and engages in simple hunter-killer warfare, shelling randomly during hunt stage*/
public class RandomAIEnemy extends AbstractAIEnemy implements Enemy {


	@Override
	public void populateBoard(GameBoard board) {
		this.board = board;

		board.setShips(new HashSet<>());
		for (int i = 0; i < 1; i++)
		{
			while (!placeShip(ShipType.CV))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.CV.name(), VerbosityLevel.GENERAL));
			}
		}
		for (int i = 0; i < 2; i++)
		{
			while (!placeShip(ShipType.BB))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.BB.name(), VerbosityLevel.GENERAL));
			}
		}
		for (int i = 0; i < 2; i++)
		{
			while (!placeShip(ShipType.CA))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.CA.name(), VerbosityLevel.GENERAL));
			}
		}
		for (int i = 0; i < 1; i++)
		{
			while (!placeShip(ShipType.SS))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.SS.name(), VerbosityLevel.GENERAL));
			}
		}

		for (int i = 0; i < 4; i++)
		{
			while (!placeShip(ShipType.DD))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.DD.name(), VerbosityLevel.GENERAL));
			}
		}

		removeShelling();
		board.updateBoard();
	}



	@Override
	public void makeHit() {
		Logger.push(new DebugMessage("Enemy turn", VerbosityLevel.GENERAL));
	}


	private boolean placeShip(ShipType type) {
		int sizeX = 0, sizeY = 0;
		switch (type)
		{
			case CV:
				sizeX = 4;
				sizeY = 1;
				break;
			case BB:
				sizeX = 3;
				sizeY = 1;
				break;
			case CA:
				sizeX = 2;
				sizeY = 1;
				break;
			case SS:
				sizeX = 2;
				sizeY = 1;
				break;
			case DD:
				sizeX = 1;
				sizeY = 1;
				break;
			case SHELLED:
				sizeX = 1;
				sizeY = 1;
				break;
		}

		Random random = new Random();

		if (random.nextBoolean())
		{
			//swap
			sizeX = sizeX + sizeY;
			sizeY = sizeX - sizeY;
			sizeX = sizeX - sizeY;
		}

		Ship ship = new Ship(random.nextInt(board.getX() - sizeX), random.nextInt(board.getY() - sizeY), sizeX, sizeY,
							 type);
		return tryPlacing(ship);

	}

}
