package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void whenUnavailable() throws Exception {
        File source = tmpFolder.newFile("server.log");
        File target = tmpFolder.newFile("unavailable.csv");
        try (PrintWriter pw = new PrintWriter(source)) {
            pw.write("200 10:56:01" + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + "200 10:59:01" + System.lineSeparator()
                    + "500 11:01:02" + System.lineSeparator()
                    + "200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(out::add);
        }
        assertThat(out.toString(), is(
                "10:57:01;10:59:01" + System.lineSeparator()
                + "11:01:02;11:02:02"
        ));
    }
}