package model.board;

import debug.DebugMessage;
import debug.Logger;
import debug.VerbosityLevel;
import model.Ship;
import model.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIBoardProvider implements BoardProvider {
    private GameBoard board;
    @Override
    public void populateBoard(GameBoard board) {
        this.board=board;

        board.setShips(new ArrayList<>());
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
        board.updateBoard();
    }

    private void removeShelling()
    {
        List<Ship> toBeRemoved = new ArrayList<>();
        for (Ship ship: board.getShips()
                )
        {
            if (ship.shipType == ShipType.SHELLED)
            {
                toBeRemoved.add(ship);
            }
        }
        board.getShips().removeAll(toBeRemoved);
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

        Ship ship = new Ship(random.nextInt(board.getX()-sizeX), random.nextInt(board.getY()-sizeY), sizeX, sizeY, type);
        return tryPlacing(ship);

    }

    private boolean tryPlacing(Ship ship)
    {
        for (int i = ship.x; i<ship.x+ship.sizeX; i++)
        {
            for(int j = ship.y; j<ship.y+ship.sizeY; j++)
            {
                if (board.getBoardArray()[i][j])
                    return false;
            }
        }

        board.getShips().add(ship);
        board.updateBoard();
        shellAroundShip(ship);
        board.updateBoard();
        return true;
    }

    private void shellAroundShip(Ship ship)
    {
        for (int i = ship.x-1; i<ship.x+ship.sizeX+1; i++)
        {
            for (int j = ship.y-1; j<ship.y+ship.sizeY+1; j++)
            {
                if ((i>=0)&&(j>=0)&&(i<board.getX())&&(j<board.getY()))
                {
                    if (!board.getBoardArray()[i][j])
                        board.getShips().add(new Ship(i, j, 1, 1, ShipType.SHELLED));
                }
            }
        }
    }
}
