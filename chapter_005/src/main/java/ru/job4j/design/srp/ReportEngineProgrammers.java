package ru.job4j.design.srp;

import org.apache.commons.text.StringEscapeUtils;

import java.util.function.Predicate;

public class ReportEngineProgrammers implements ReportEngine {
    private Store store;
    private ReportEngine old;

    public ReportEngineProgrammers(Store store) {
        this.store = store;
        old = new ReportEngineOld(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return StringEscapeUtils.escapeHtml4(old.generate(filter));
    }
}
