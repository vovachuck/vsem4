package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
  @author   Volodymyr Lakusta
  @project   vsem4
  @class  LogService
  @version  1.0.0 
  @since 14.04.2021 - 19.15
**/

public class LogsManager{
    private String file;

    public LogsManager() {
    }

    public LogsManager(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public static void errorsLogsByDateToFile(String file, LocalDate date) throws IOException {

        String str = Files.lines(Paths.get(file))
                .filter(log -> log.contains(date.toString()) && log.contains("ERROR"))
                .collect(Collectors.joining("\n"));

        String fileOutput = "src/com/company/" +  "ERROR" + date.toString() + ".log";
        Files.write(Paths.get(fileOutput), str.getBytes());

    }

}
