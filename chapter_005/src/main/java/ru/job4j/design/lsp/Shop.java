package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Shop implements Store {
    private List<Food> products = new ArrayList<>();
    private final int discount = 20;

    @Override
    public void add(Food food) {
        Function<Food, Boolean> fucn = ControllQuality.countStorageTime(
                (fullDate, current) -> current >= 0.25 * fullDate && current <= 0.75 * fullDate);
        if (fucn.apply(food)) {
            food.setDiscount(discount);
        }
        products.add(food);
    }

    @Override
    public List<Food> getAll() {
        return products;
    }
}
