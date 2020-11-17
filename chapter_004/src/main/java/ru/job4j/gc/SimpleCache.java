package ru.job4j.gc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleCache {
    private Map<String, SoftReference> cache;
    private SoftReference<List<String>> names;
    private SoftReference<List<String>> addresses;

    public SimpleCache() {
        cache = new HashMap<>();
        cache.put("Names.txt", names);
        cache.put("Addresses.txt", addresses);
    }

    public List<String> get(String key) throws IOException {
        SoftReference<List<String>> softRef = cache.get(key);
        if (softRef == null || softRef.get() == null) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader("chapter_004/src/forCache/" + key))) {
                List<String> fileContent = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    fileContent.add(line);
                }
                softRef = new SoftReference<>(fileContent);
            }
        }
        return softRef.get();
    }

    public static void main(String[] args) throws Exception {
        SimpleCache simpleCache = new SimpleCache();
        System.out.println(simpleCache.get("Names.txt"));
        System.out.println(simpleCache.get("Adresses.txt"));
    }
}
