package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapUsage {
    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User user1 = new User("Dima", 0, new GregorianCalendar(1997, Calendar.NOVEMBER, 4));
        User user2 = new User("Dima", 0, new GregorianCalendar(1997, Calendar.NOVEMBER, 4));
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
