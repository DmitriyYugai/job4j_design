package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CinemaTest {
    @Ignore
    @Test
    public void whenBuyThenOk() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test(expected = NoSuchElementException.class)
    public void whenBuyThenNoTickets() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, 01, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test
    public void whenFindThenOk() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test(expected = NoSuchElementException.class)
    public void whenFindThenNoSession() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
    }
}