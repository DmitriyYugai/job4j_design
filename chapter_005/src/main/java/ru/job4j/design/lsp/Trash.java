package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private List<Food> products = new ArrayList<>();

    @Override
    public void add(Food food) {
        products.add(food);
    }
}
