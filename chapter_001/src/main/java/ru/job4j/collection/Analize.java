package ru.job4j.collection;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        List<User> delPrev = new ArrayList<>(previous);
        List<User> addCur = new ArrayList<>(current);
        List<User> changePrev = new ArrayList<>(previous);
        List<User> changeCur = new ArrayList<>(current);
        Set<User> set = new HashSet<>();
        Info info = new Info();
        delPrev.removeAll(current);
        info.deleted = delPrev.size();
        addCur.removeAll(previous);
        info.added = addCur.size();
        changePrev.retainAll(current);
        changeCur.retainAll(previous);
        set.addAll(changePrev);
        set.addAll(changeCur);
        info.changed = set.size() - changePrev.size();
        return info;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info() {
        }

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }
}
