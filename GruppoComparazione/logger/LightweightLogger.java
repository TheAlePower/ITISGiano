package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LightweightLogger {
    private static File outputFile;

    public static void init() {
        new File(".\\logs").mkdir();
        outputFile = new File(".\\logs\\" + LocalDateTime.now().getYear() + "_" +  LocalDateTime.now().getMonth() + "_" + LocalDateTime.now().getDayOfMonth() + "@" + LocalDateTime.now().getHour() + "_" + LocalDateTime.now().getMinute() + "_" + LocalDateTime.now().getSecond() + ".txt");
        try {
            outputFile.createNewFile();
        } catch (IOException abcdefg) {}

        writeLog("Logging started! First log timestamp > " + LocalDateTime.now().getYear() + "_" +  LocalDateTime.now().getMonth() + "_" + LocalDateTime.now().getDayOfMonth() + "@" + LocalDateTime.now().getHour() + "_" + LocalDateTime.now().getMinute() + "_" + LocalDateTime.now().getSecond());
    }

    public static void writeLog(String log) {
        try {
            FileWriter out = new FileWriter(outputFile,true);
            out.append(log).append("\n");
            out.close();
            System.out.println(log);
        } catch (IOException abcdefg) {}
    }
}
