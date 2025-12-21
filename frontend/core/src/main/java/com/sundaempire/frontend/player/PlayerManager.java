package com.sundaempire.frontend.player;

import com.sundaempire.frontend.gamemanager.GameActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private Map<GameActor, Player> players = new HashMap<>();

    public PlayerManager() {
        players.put(GameActor.PLAYER_1, new Player(GameActor.PLAYER_1));
    }

    public Player getPlayer(GameActor gameActor) {
        return players.get(gameActor);
    }
}
