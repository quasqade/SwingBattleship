package model.enemy;

import model.board.GameBoard;

public class AIEnemy implements Enemy {
    private EnemyType type = EnemyType.AI;

    @Override
    public void processTurn(GameBoard board) {

    }

    @Override
    public GameBoard makeTurn() {
        return null;
    }
}
