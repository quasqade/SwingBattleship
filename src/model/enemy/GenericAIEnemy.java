package model.enemy;

import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import model.Ship;
import model.ShipType;
import model.board.GameBoard;

import java.util.ArrayList;
import java.util.List;

/*This class represents abstract AI Enemy that should be extended
 *to create AI enemies of different skill level*/

public abstract class GenericAIEnemy {
	public GameBoard board;


	public void processHit(
			int x,
			int y) {
		if (board.getBoardArray()[x][y])
		{
			Ship sunkShip = null; //we store sunk ship to avoid concurrent modification error
			//If ship is potentially hit
			//parse ship list to determine if previous shelling or ship, and then if sunk or not
			for (Ship ship : board.getShips()
					)
			{
				for (int i = ship.x; i < ship.x + ship.sizeX; i++)
				{
					for (int j = ship.y; j < ship.y + ship.sizeY; j++)
					{
						if (x == i && y == j)
						{
							if (ship.shipType == ShipType.SHELLED)
							{
								//Hit already shelled spot
								board.updateBoard();
							}
							else
							{
								//Hit a ship
								if (ship.isSunk())
								{
									Logger.push(new DebugMessage("Stop hitting dead ships", VerbosityLevel.GENERAL));
								}
								else if (ship.isBurning(i, j))
								{
									Logger.push(new DebugMessage("Stop hitting the same spot", VerbosityLevel.GENERAL));
								}
								else
								{
									ship.shellSpot(i, j);
									if (ship.isSunk())
									{
										sunkShip = ship;
									}
									board.updateBoard();
									Logger.push(
											new DebugMessage("Hit a ship at " + x + ", " + y, VerbosityLevel.GENERAL));
								}
							}
						}
					}
				}
			}
			if (sunkShip != null)
			{
				Logger.push(new DebugMessage("Sunk a " + sunkShip.shipType.name(), VerbosityLevel.GENERAL));
				shellAroundShip(sunkShip);
				board.updateBoard();
			}
		}
		else
		{
			//If ship is definitely not hit
			board.getShips().add(new Ship(1, 1, 1, 1, ShipType.SHELLED));
			board.updateBoard();
		}

	}


	public void removeShelling() {
		List<Ship> toBeRemoved = new ArrayList<>();
		for (Ship ship : board.getShips()
				)
		{
			if (ship.shipType == ShipType.SHELLED)
			{
				toBeRemoved.add(ship);
			}
		}
		board.getShips().removeAll(toBeRemoved);
	}

	public boolean tryPlacing(Ship ship) {
		for (int i = ship.x; i < ship.x + ship.sizeX; i++)
		{
			for (int j = ship.y; j < ship.y + ship.sizeY; j++)
			{
				if (board.getBoardArray()[i][j])
				{
					return false;
				}
			}
		}

		board.getShips().add(ship);
		board.updateBoard();
		shellAroundShip(ship);
		board.updateBoard();
		return true;
	}


	public void shellAroundShip(Ship ship) {
		for (int i = ship.x - 1; i < ship.x + ship.sizeX + 1; i++)
		{
			for (int j = ship.y - 1; j < ship.y + ship.sizeY + 1; j++)
			{
				if ((i >= 0) && (j >= 0) && (i < board.getX()) && (j < board.getY()))
				{
					if (!board.getBoardArray()[i][j])
					{
						board.getShips().add(new Ship(i, j, 1, 1, ShipType.SHELLED));
					}
				}
			}
		}
	}

}
