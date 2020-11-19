package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportEngineJson extends ReportEngine {

    public ReportEngineJson(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(super.generate(filter));
    }
}
