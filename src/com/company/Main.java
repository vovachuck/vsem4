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
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        final String PATH = "src/com/company/logs2019.log";

        String logs = new String(Files.readAllBytes(Paths.get(PATH)));
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

        System.out.println(ChronoUnit.MILLIS.between( start, finish));

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

