package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportEngineAccounters extends ReportEngine {

    public ReportEngineAccounters(Store store) {
        super(store);
    }

    @Override
    public StringBuilder appendObj(StringBuilder sb, Object name,
                                   Object hired, Object fired, Object salary) {
        if (salary instanceof Double) {
            Double salaryDoub = (Double) salary;
            salaryDoub *= 12;
            return super.appendObj(sb, name, hired, fired, salaryDoub);
        }
        return super.appendObj(sb, name, hired, fired, salary);
    }

}
