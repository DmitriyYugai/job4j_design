package ru.job4j.io;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final Random rand = new Random();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean stopFlag = false;
        String input = null;
        while (!(input = scanner.nextLine()).equals(OUT)) {
            try (PrintWriter pw = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(new File("chapter_002/data/chat_log.txt"), true)
                    )
            )) {
                pw.write("Пользователь: " + input + System.lineSeparator());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (input.equals(STOP)) {
                stopFlag = true;
                continue;
            }
            if (input.equals(CONTINUE)) {
                stopFlag = false;
            }
            if (!stopFlag) {
                String answer = getBotAnswer();
                try (PrintWriter pw = new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(new File("chapter_002/data/chat_log.txt"), true)
                        )
                )) {
                    pw.write("Бот: "  + answer + System.lineSeparator());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(answer);
            }
        }
    }

    private String getBotAnswer() {
        int r = rand.nextInt(7);
        String answer = null;
        try (BufferedReader br = new BufferedReader(
                new FileReader(new File("chapter_002/data/chat_bot_answers.txt"))
        )) {
            for (int i = 0; i <= r; i++) {
                answer = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "chapter_002/data/chat_bot_answers.txt", "chapter_002/data/chat_log.txt");
        cc.run();
    }
}
