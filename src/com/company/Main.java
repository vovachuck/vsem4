package com.company;
/**
  @author   Volodymyr Lakusta
  @project   vsem4
  @class  Main
  @version  1.0.0
  @since 14.04.2021 - 20.51
**/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        final String PATH = "src/com/company/logs.txt";

        LocalDateTime start = LocalDateTime.now();
        LocalDate date = LocalDate.of(2020, Month.FEBRUARY, 14);

        for (int i = 0; i < 5; i++) {
            LogsManager.errorsLogsByDateToFile(PATH,date.plusDays(i));
        }

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("Consistent way: " + ChronoUnit.MILLIS.between(start, finish));

        start = LocalDateTime.now();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogsManager.errorsLogsByDateToFile(PATH,date.plusDays(finalI));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.join();
        }
        finish = LocalDateTime.now();
        System.out.println("Multi-threading way: " + ChronoUnit.MILLIS.between(start, finish));

        /*Output:
            Consistent way: 9860
            Multi-threading way: 8
         */

        /*String logs = new String(Files.readAllBytes(Paths.get(PATH)));
        final String[] lines = logs.split("\n");

        System.out.println(lines.length);
        System.out.println("String.split-------------");
        LocalDateTime start = LocalDateTime.now();
        int counter = 0;
        for (int i = 0; i < lines.length ; i++) {
            if (lines[i].contains("ERROR")) counter++;
        }
        System.out.println("ERRORS  - " + counter);
        LocalDateTime finish = LocalDateTime.now();
        System.out.println(ChronoUnit.MILLIS.between( start, finish));

        System.out.println("File.Lines-------------");

        start = LocalDateTime.now();
        System.out.println(Files.lines(Paths.get(PATH)).filter(e -> e.contains("ERROR")).count());
        finish = LocalDateTime.now();

        System.out.println(ChronoUnit.MILLIS.between( start, finish));*/

    }
    /* Output:
    433387
    String.split-------------
    ERRORS  - 105
    151
    File.Lines-------------
    105
    725
     */

}

