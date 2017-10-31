package model.board;

import model.Ship;

import java.util.List;

public interface BoardProvider {

    public void populateBoard(GameBoard board);

    public void processHit(int x, int y);

}
