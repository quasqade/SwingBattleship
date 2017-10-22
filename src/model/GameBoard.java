package model;

import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * GameBoard in concept is dimensions, and list of ships and shelled spots (represented as 1x1 ship with special type)
 * Note: 2d array should NEVER be populated directly, it serves only to speed up collision checking
 */
public class GameBoard
{
	private int x,y;
	private List<Ship> ships;
	private boolean[][] boardArray;

	public GameBoard(int x, int y)
	{
		this.x=x;
		this.y=y;
		populate();
		printBoard();
	}

	public void populate()
	{
		boardArray = new boolean[y][x]; //Note this!
		ships = new ArrayList<>();
		for (int i = 0; i<1; i++)
		{
			while(!placeShip(ShipType.CV))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.CV.name(), VerbosityLevel.GENERAL));
			}
		}
		for (int i = 0; i<2; i++)
		{
			while(!placeShip(ShipType.BB))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.BB.name(), VerbosityLevel.GENERAL));
			}
		}
		for (int i = 0; i<2; i++)
		{
			while(!placeShip(ShipType.CA))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.CA.name(), VerbosityLevel.GENERAL));
			}
		}
		for (int i = 0; i<1; i++)
		{
			while(!placeShip(ShipType.SS))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.SS.name(), VerbosityLevel.GENERAL));
			}
		}

		for (int i = 0; i<4; i++)
		{
			while(!placeShip(ShipType.DD))
			{
				Logger.push(new DebugMessage("Failed to place a " + ShipType.DD.name(), VerbosityLevel.GENERAL));
			}
		}

		removeShelling();
		updateBoard();
	}

	private boolean placeShip(ShipType type)
	{
		int sizeX=0, sizeY=0;
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
			sizeX = sizeX+sizeY;
			sizeY = sizeX-sizeY;
			sizeX = sizeX-sizeY;
		}

		Ship ship = new Ship(random.nextInt(x-sizeX), random.nextInt(y-sizeY), sizeX, sizeY, type);
		return tryPlacing(ship);

	}

	private boolean tryPlacing(Ship ship)
	{
		for (int i = ship.x; i<ship.x+ship.sizeX; i++)
		{
			for(int j = ship.y; j<ship.y+ship.sizeY; j++)
			{
				if (boardArray[i][j])
					return false;
			}
		}

		ships.add(ship);
		updateBoard();
		shellAroundShip(ship);
		updateBoard();
		return true;
	}

	private void shellAroundShip(Ship ship)
	{
		for (int i = ship.x-1; i<ship.x+ship.sizeX+1; i++)
		{
			for (int j = ship.y-1; j<ship.y+ship.sizeY+1; j++)
			{
				if ((i>=0)&&(j>=0)&&(i<x)&&(j<y))
				{
					if (!boardArray[i][j])
						ships.add(new Ship(i, j, 1, 1, ShipType.SHELLED));
				}
			}
		}
	}



	private void updateBoard()
	{

		for (int i = 0; i < boardArray.length; i++){
			Arrays.fill(boardArray[i], false);
		}
		for (Ship ship: ships
			 )
		{
			for (int i = ship.x; i<ship.x+ship.sizeX; i++)
			{
				for (int j = ship.y; j < ship.y+ship.sizeY; j++)
				{
					boardArray[i][j] = true;
				}
			}
		}
	}

	private void removeShelling()
	{
		List<Ship> toBeRemoved = new ArrayList<>();
		for (Ship ship: ships
			 )
		{
			if (ship.shipType == ShipType.SHELLED)
			{
				toBeRemoved.add(ship);
			}
		}
		ships.removeAll(toBeRemoved);
	}

	private void printBoard()
	{
		System.out.println();
		//fancy header
		String str = "GAME BOARD";
		int size = (x+1)*4;
		int strBeg = size/2-(str.length()+2)/2;

		for (int i = 0; i< size; i++)
		{
			System.out.printf("=");
		}

		System.out.println();


		for (int i = 0; i< strBeg; i++)
		{
			System.out.printf("=");
		}
		System.out.printf(" " + str + " ");
		for (int i = strBeg+str.length()+2; i< size; i++)
		{
			System.out.printf("=");
		}
		System.out.println();

		for (int i = 0; i< size; i++)
		{
			System.out.printf("=");
		}
		System.out.println();



		System.out.printf("%2s |", " ");
		for (int i = 0; i< x; i++)
		{
			System.out.printf("%3d|", i);
		}
		System.out.println();
		for (int i = 0; i<x; i++)
		{
			System.out.printf("%2d |", i);
			for (int j = 0; j < y; j++)
			{
				if (boardArray[j][i])
					System.out.printf("%3s|", " x ");
				else
				System.out.printf("%3s|", " ");
			}
			System.out.println();
		}
		System.out.println();
	}



}
