package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class ControllQuality {
    private Map<Function<Food, Boolean>, Store> dispatch;

    public ControllQuality() {
        dispatch = new LinkedHashMap<>();
        initDispatch();
    }

    public void distribute(Food food) {
        Optional<Store> store = getStore(food);
        store.ifPresent(value -> value.add(food));
    }

    private Optional<Store> getStore(Food food) {
        for (Function<Food, Boolean> f : dispatch.keySet()) {
            if (f.apply(food)) {
                return Optional.of(dispatch.get(f));
            }
        }
        return Optional.empty();
    }

    private void initDispatch() {
        dispatch.put(countStorageTime(
                (fullDate, current) -> current < 0.25 * fullDate), new Warehouse());
        dispatch.put(countStorageTime(
                (fullDate, current) -> current >= 0.25 * fullDate), new Shop());
        dispatch.put(countStorageTime(
                (fullDate, current) -> current >= fullDate), new Trash());
    }

    static Function<Food, Boolean> countStorageTime(BiPredicate<Long, Long> pred) {
        return food -> {
            long fullDate = ChronoUnit.DAYS.between(food.getExpaireDate(), food.getCreateDate());
            long current = ChronoUnit.DAYS.between(LocalDate.now(), food.getCreateDate());
            return pred.test(fullDate, current);
        };
    }
}
