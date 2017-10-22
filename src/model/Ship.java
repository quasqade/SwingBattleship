package model;

/**
 * Ship consists of two coordinates, two dimensions and type (for scoring and future rendering)
 */
public class Ship
{
	public ShipType shipType;
	public int x,y,sizeX, sizeY;

	public Ship(int x, int y, int sizeX, int sizeY, ShipType shipType)
	{
		this.x=x;
		this.y=y;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.shipType=shipType;
	}

}
