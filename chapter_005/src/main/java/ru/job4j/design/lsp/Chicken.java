package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Chicken extends Food {
    public Chicken(String name, LocalDate expaireDate,
                   LocalDate createDate, double price, double discount) {
        super(name, expaireDate, createDate, price, discount);
    }
}
