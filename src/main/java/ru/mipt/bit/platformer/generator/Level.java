package ru.mipt.bit.platformer.generator;

import ru.mipt.bit.platformer.event.EventListener;
import ru.mipt.bit.platformer.event.EventPublisher;
import ru.mipt.bit.platformer.event.EventType;
import ru.mipt.bit.platformer.gameobjects.Bullet;
import ru.mipt.bit.platformer.gameobjects.Tank;
import ru.mipt.bit.platformer.gameobjects.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Use-case
 */
public class Level implements EventPublisher {
    private Tank playerTank = null;
    private final List<Tree> trees = new CopyOnWriteArrayList<>();
    private final List<Tank> tanks = new CopyOnWriteArrayList<>();
    private final Queue<Bullet> bullets = new ConcurrentLinkedDeque<>();
    private final Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public Level(List<EventType> eventTypes) {
        eventTypes.forEach(event -> listeners.put(event, new ArrayList<>()));
    }

    public void addAllTanks(List<Tank> tanks) {
        this.tanks.addAll(tanks);
    }

    public void addAllTrees(List<Tree> trees) {
        this.trees.addAll(trees);
    }

    public void addPlayerTank(Tank tank) {
        this.playerTank = tank;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public void registerBulletCreation(Bullet bullet) {
        bullets.add(bullet);
        notifySubs(EventType.ADD_BULLET, bullet);
    }

    public void registerBulletDestruction(Bullet bullet) {
        bullets.remove(bullet);
        notifySubs(EventType.REMOVE_BULLET, bullet);
    }

    public void registerTankDestruction(Tank tank) {
        if (playerTank == tank) {

        } else {
            tanks.remove(tank);
        }
        notifySubs(EventType.REMOVE_TANK, tank);
    }

    public Queue<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void subscribe(EventType eventType, EventListener eventListener) {
        List<EventListener> listenersByType = listeners.get(eventType);
        listenersByType.add(eventListener);
    }

    @Override
    public void unsubscribe(EventType eventType, EventListener eventListener) {
        List<EventListener> listenersByType = listeners.get(eventType);
        listenersByType.remove(eventListener);
    }

    @Override
    public void notifySubs(EventType eventType, Object object) {
        List<EventListener> listenersByType = listeners.get(eventType);
        for (EventListener listener : listenersByType) {
            listener.update(eventType, object);
        }
    }
}
