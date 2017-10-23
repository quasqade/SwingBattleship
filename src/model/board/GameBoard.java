package model.board;

import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import model.Ship;
import model.ShipType;

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
		boardArray = new boolean[y][x];
	}





	public void updateBoard()
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



	public void printBoard()
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public boolean[][] getBoardArray() {
		return boardArray;
	}

	public void setBoardArray(boolean[][] boardArray) {
		this.boardArray = boardArray;
	}
}
