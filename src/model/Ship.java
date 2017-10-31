package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Ship consists of two coordinates, two dimensions, type (for scoring and future rendering) and coords of burning spots
 */
public class Ship
{
	public ShipType shipType;
	public int x,y,sizeX, sizeY;
	public List<Cell> burningSpots;

	public Ship(int x, int y, int sizeX, int sizeY, ShipType shipType)
	{
		this.x=x;
		this.y=y;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.shipType=shipType;
	}

	public void shellSpot(int x, int y)
	{
		if (burningSpots==null)
			burningSpots=new ArrayList<>();
		burningSpots.add(new Cell(x,y));
	}

	public boolean isBurning(int x, int y)
	{
		if (burningSpots==null)
			return false;
		for (Cell cell: burningSpots
			 ) {
			if (cell.x == x && cell.y==y)
			{
				return true;
			}
		}
		return false;
	}

	public boolean isSunk()
	{

		for (int i = x; i<x+sizeX; i++)
		{
			for (int j = y; j < y + sizeY; j++)
			{
				if (!isBurning(i, j))
					return false;
			}
		}
		return true;
	}

}
