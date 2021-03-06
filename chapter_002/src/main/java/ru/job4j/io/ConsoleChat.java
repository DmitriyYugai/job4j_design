package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final Random rand = new Random();
    private List<String> botList = new ArrayList<>();
    private List<String> logList = new ArrayList<>();
    private Map<String, Predicate<Boolean>> dispatch = new HashMap<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        dispatch.put(STOP, b -> true);
        dispatch.put(CONTINUE, b -> false);
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    public void run() {
        writeAnswers();
        Scanner scanner = new Scanner(System.in);
        boolean stopFlag = false;
        String input = null;
        while (!(input = scanner.nextLine()).equals(OUT)) {
            logList.add("Пользователь: " + input);
            stopFlag = getDispatch(input, stopFlag);
            if (!stopFlag) {
                String answer = getBotAnswer();
                logList.add("Бот: " + answer);
                System.out.println(answer);
            }
        }
        logList.add("Пользователь: " + input);
        writeLog();
    }

    private String getBotAnswer() {
        int r = Math.abs(rand.nextInt(botList.size()));
        return botList.get(r);
    }

    private void writeAnswers() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(new File(botAnswers))
        )) {
            String answer;
            while ((answer = br.readLine()) != null) {
                botList.add(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLog() {
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(new File(path), false)
                )
        )) {
            for (String s : logList) {
                pw.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean getDispatch(String input, boolean stopFlag) {
        return dispatch.getOrDefault(input, flag -> flag).test(stopFlag);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "chapter_002/data/chat_log.txt", "chapter_002/data/chat_bot_answers.txt");
        cc.run();
    }
}
