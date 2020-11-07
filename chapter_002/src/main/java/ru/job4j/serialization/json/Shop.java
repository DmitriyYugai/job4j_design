package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public int getId() {
        return id;
    }

    public boolean isOpened() {
        return opened;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }

    public int[] getCodes() {
        return codes;
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
//        Gson gson = new GsonBuilder().create();
//        Shop shop = new Shop(
//                3, true, "Shop", new Product("bread", 40), new int[]{5, 7, 10});
//        System.out.println(gson.toJson(shop));
//
//        String json = "{"
//                + "\"id\":4,"
//                + "\"opened\":false,"
//                + "\"name\":\"Lenta\","
//                + "\"product\":"
//                    + "{"
//                            + "\"name\":\"Banana\","
//                            + "\"price\":50"
//                    + "},"
//                + "\"codes\":[12, 31, 54]"
//                + "}";
//        System.out.println(gson.fromJson(json, Shop.class));

        JSONObject jsonProduct = new JSONObject("{"
                        + "\"name\":\"Chocolate\","
                        + "\"price\":200"
                        + "}"
                );
        System.out.println(jsonProduct);

        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(567);
        list.add(123);
        list.add(678);
        JSONArray jsonCodes = new JSONArray(list);
        System.out.println(jsonCodes);

        JSONObject jsonShop = new JSONObject();
        jsonShop.put("id", 666);
        jsonShop.put("opened", true);
        jsonShop.put("name", "XXX");
        jsonShop.put("product", jsonProduct);
        jsonShop.put("codes", jsonCodes);
        System.out.println(jsonShop);

        Shop shop = new Shop(
                3, true, "Shop", new Product("bread", 40), new int[]{5, 7, 10});
        System.out.println(new JSONObject(shop).toString());
    }
}
