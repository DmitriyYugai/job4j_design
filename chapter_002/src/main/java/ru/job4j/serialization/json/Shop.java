package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Shop {
    private int id;
    private boolean opened;
    private String name;
    private Product product;
    private int[] codes;

    public Shop(int id, boolean opened, String name, Product product, int[] codes) {
        this.id = id;
        this.opened = opened;
        this.name = name;
        this.product = product;
        this.codes = codes;
    }

    @Override
    public String toString() {
        return "Shop{"
                + "id=" + id
                + ", opened=" + opened
                + ", name='" + name + '\''
                + ", product=" + product
                + ", codes=" + Arrays.toString(codes)
                + '}';
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        Shop shop = new Shop(
                3, true, "Shop", new Product("bread", 40), new int[]{5, 7, 10});
        System.out.println(gson.toJson(shop));

        String json = "{"
                + "\"id\":4,"
                + "\"opened\":false,"
                + "\"name\":\"Lenta\","
                + "\"product\":"
                    + "{"
                            + "\"name\":\"Banana\","
                            + "\"price\":50"
                    + "},"
                + "\"codes\":[12, 31, 54]"
                + "}";
        System.out.println(gson.fromJson(json, Shop.class));
    }
}
