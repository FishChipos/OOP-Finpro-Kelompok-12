package com.sundaempire.frontend.gamemanager;

public enum GameActor {
    PLAYER_1;
//    PLAYER_2,
//    PLAYER_3,
//    PLAYER_4,
//    AI_1,
//    AI_2,
//    AI_3,
//    AI_4;

    private boolean enabled = false;
    private static final GameActor[] values = values();

    public void enable(GameActor gameActor, boolean enabled) {
        this.enabled = enabled;
    }

    public GameActor nextGameActor() {
        return values[(this.ordinal() + 1) % values.length];
    }
}
