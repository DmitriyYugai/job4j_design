package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class ConcurrentCache {
    private ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();
    private AtomicInteger ver = new AtomicInteger();

    public boolean add(Base model) {
        return map.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return map.computeIfPresent(model.getId(), (key, value) -> {
            ver.set(value.getVersion());
            int version = ver.get();
            if (version == model.getVersion() || !ver.compareAndSet(version, model.getVersion())) {
                throw new OptimisticException("Invalid version");
            }
            return model;
        }) != null;
    }

    public boolean delete(Base model) {
        return map.entrySet().removeIf(entry -> entry.getKey() == model.getId());
    }
}
