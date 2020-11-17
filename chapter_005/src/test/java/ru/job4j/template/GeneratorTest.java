package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenProduceThenOk() {
        Generator gen = new GeneratorImpl();
        String rsl = gen.produce("I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev", "subject", "you"));
        assertThat(rsl, is("I am a Petr Arsentev, Who are you? "));
    }

    @Ignore
    @Test(expected = NoSuchElementException.class)
    public void whenProduceThenNoKeys() {
        Generator gen = new GeneratorImpl();
        gen.produce("I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenProduceThenExcessKeys() {
        Generator gen = new GeneratorImpl();
        gen.produce("I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev", "subject", "you", "surname", "Arsentev"));
    }
}