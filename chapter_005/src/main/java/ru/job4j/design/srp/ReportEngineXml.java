package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportEngineXml extends ReportEngine {

    public ReportEngineXml(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jc = JAXBContext.newInstance();
            Marshaller m = jc.createMarshaller();
            m.marshal(super.generate(filter), sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
