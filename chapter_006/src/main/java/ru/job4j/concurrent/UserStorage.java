package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final HashMap<Integer, User> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        return (storage.putIfAbsent(user.getId(), user)) == null;
    }

    public synchronized boolean update(User user) {
        if (storage.containsKey(user.getId())) {
            storage.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public  synchronized boolean delete(User user) {
        return storage.entrySet().removeIf(entry -> entry.getKey() == user.getId());
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        return transferAmount(fromId, sum -> sum -= amount)
                && transferAmount(toId, sum -> sum += amount);
    }

    private synchronized boolean transferAmount(int id, UnaryOperator<Integer> func) {
        if (!storage.containsKey(id)) {
            return false;
        }
        User user = storage.get(id);
        int userAmount = user.getAmount();
        userAmount = func.apply(userAmount);
        user.setAmount(userAmount);
        return true;
    }
}
