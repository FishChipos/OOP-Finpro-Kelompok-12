package com.sundaempire.frontend.player;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.Notifiable;
import com.sundaempire.frontend.Observable;
import com.sundaempire.frontend.gamemanager.GameActor;
import com.sundaempire.frontend.gamemap.GameMap;
import com.sundaempire.frontend.settlement.Settlement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager implements Observable {
    private Map<GameActor, Player> players = new HashMap<>();
    private List<Notifiable> observers = new ArrayList<>();

    public PlayerManager() {
        players.put(GameActor.PLAYER_1, new Player(GameActor.PLAYER_1, this
        ));
    }

    public Player getPlayer(GameActor gameActor) {
        return players.get(gameActor);
    }

    @Override
    public void addObserver(Notifiable observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Notifiable observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Notifiable observer : observers) {
            observer.notice();
        }
    }

    public Settlement getSettlement(Vector2 coordinate) {
        for (GameActor gameActor : GameActor.values()) {
            Player player = players.get(gameActor);

            for (Settlement settlement : player.getSettlements()) {
                if (settlement.getCoordinates().equals(coordinate)) {
                    return settlement;
                }
            }
        }

        return null;
    }
}
