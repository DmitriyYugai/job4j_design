package ru.job4j.design.srp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngineHR extends ReportEngine {

    public ReportEngineHR(Store store) {
        super(store);
    }

    @Override
    protected StringBuilder appendString(StringBuilder sb,
                                         String name, String hired, String fired, String salary) {
        return append(sb, name, salary);
    }

    @Override
    protected StringBuilder appendObj(StringBuilder sb,
                                      Object name, Object hired, Object fired, Object salary) {
        return append(sb, name, salary);
    }

    @Override
    protected List<Employee> sortList(List<Employee> list) {
        list.sort(Collections.reverseOrder(Comparator.comparing(Employee::getSalary)));
        return list;
    }

}
