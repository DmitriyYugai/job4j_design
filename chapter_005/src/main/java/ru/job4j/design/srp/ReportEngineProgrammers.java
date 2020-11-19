package ru.job4j.design.srp;

import org.apache.commons.text.StringEscapeUtils;

import java.util.function.Predicate;

public class ReportEngineProgrammers extends ReportEngine {

    public ReportEngineProgrammers(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return StringEscapeUtils.escapeHtml4(super.generate(filter));
    }
}
