package model.enemy;

import model.Ship;
import model.board.GameBoard;

import java.util.List;

/*This interface should be implemented by Enemies, be it AI opponent, network opponent or otherwise*/

public interface Enemy {

    public void populateBoard(GameBoard board);

    public void processHit(int x, int y);

    public void makeHit();
}
