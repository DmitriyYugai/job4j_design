package ru.job4j.collection;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        int remains = 0;
        for (User userPrev : previous) {
            int index = findById(userPrev.id, current);
            if (index == -1) {
                info.deleted++;
                continue;
            }
            remains++;
            User userCur = current.get(index);
            if (!userCur.name.equals(userPrev.name)) {
                info.changed++;
            }
        }
        info.added = current.size() - remains;
        return info;
    }

    private int findById(int id, List<User> list) {
        int rsl = -1;
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).id == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
