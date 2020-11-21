package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Banana extends Food {
    public Banana(String name, LocalDate expaireDate,
                  LocalDate createDate, double price, double discount) {
        super(name, expaireDate, createDate, price, discount);
    }
}
