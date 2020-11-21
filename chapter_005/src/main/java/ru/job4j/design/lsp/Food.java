package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Food {
    private String name;
    private LocalDate expaireDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDate expaireDate,
                LocalDate createDate, double price, double discount) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpaireDate() {
        return expaireDate;
    }

    public void setExpaireDate(LocalDate expaireDate) {
        this.expaireDate = expaireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
