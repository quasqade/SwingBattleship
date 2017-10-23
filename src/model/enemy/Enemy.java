package model.enemy;

import model.board.GameBoard;

public interface Enemy {
    public void processTurn(GameBoard board);

    public GameBoard makeTurn();
}
