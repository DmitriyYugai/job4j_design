package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text = appendString(text, "Name", "Hired", "Fired", "Salary");
        List<Employee> sortedList = sortList(store.findBy(filter));
        for (Employee employee : sortedList) {
            text = appendObj(text, employee.getName(), employee.getHired(),
                    employee.getFired(), employee.getSalary());
        }
        return text.toString();
    }

    protected StringBuilder appendString(StringBuilder sb,
                                      String name, String hired, String fired, String salary) {
        return append(sb, name, hired, fired, salary);
    }

    protected StringBuilder appendObj(StringBuilder sb,
                                      Object name, Object hired, Object fired, Object salary) {
        return append(sb, name, hired, fired, salary);
    }

    protected List<Employee> sortList(List<Employee> list) {
        return list;
    }

    protected StringBuilder append(StringBuilder sb,
                   Object... args) {
        for (Object obj : args) {
            sb.append(obj).append("; ");
        }
        return sb.append(System.lineSeparator());
    }

}
