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

public class Level implements EventPublisher {
    private Tank playerTank = null;
    private final List<Tree> trees = new CopyOnWriteArrayList<>();
    private final List<Tank> tanks = new CopyOnWriteArrayList<>();
    private final Queue<Bullet> bullets = new ConcurrentLinkedDeque<>();
    private final Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public Level(List<EventType> eventTypes) {
        eventTypes.forEach(event -> listeners.put(event, new ArrayList<>()));
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

    public void registerTreeCreation(Tree tree) {
        trees.add(tree);
        notifySubs(EventType.ADD_TREE, tree);
    }

    public void registerTreeDestruction(Tree tree) {
        trees.remove(tree);
        notifySubs(EventType.REMOVE_TREE, tree);
    }

    public void registerTankCreation(Tank tank) {
        tanks.add(tank);
        notifySubs(EventType.ADD_TANK, tank);
    }

    public void registerPlayerTankCreation(Tank tank) {
        playerTank = tank;
        notifySubs(EventType.ADD_PLAYER_TANK, tank);
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
            //game over
            notifySubs(EventType.REMOVE_PLAYER_TANK, tank);
        } else {
            tanks.remove(tank);
            notifySubs(EventType.REMOVE_TANK, tank);
        }
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
