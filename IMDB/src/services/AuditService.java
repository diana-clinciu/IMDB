package services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class AuditService {
    private static final String CSV_FILE_PATH = "audit.csv";
    private FileWriter fileWriter;

    public AuditService() {
        try {
            fileWriter = new FileWriter(CSV_FILE_PATH, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void recordActionToCSV(String actionName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);
            String record = actionName + "," + timestamp + System.lineSeparator();
            fileWriter.write(record);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
