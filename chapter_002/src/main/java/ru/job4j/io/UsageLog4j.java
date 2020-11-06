package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = false;
        byte b = 24;
        short s = 123;
        char c = 'x';
        int i = 4567;
        long l = 123456;
        float f = 142.2f;
        double d = 6436.1245;
        LOG.debug("boolean: {}, byte: {}, short: {}, char: {}, "
                + "int: {}, long: {}, float: {}, double: {}", bool, b, s, c, i, l, f, d);
    }
}
