package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Dmitry")
        );
        assertThat(
                config.value("surname"),
                is("Yugay")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Dmitry")
        );
        assertThat(
                config.value("surname"),
                is("Yugay")
        );
        assertThat(
                config.value("BirthDay"),
                is("11.11.11")
        );
    }
}