import java.io.File;
import java.io.FileWriter;

public class FileEditor {
    public static void createFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void writeFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    public static void deleteDirectory(String directoryName) {
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        for (File file : files) {
            file.delete();
        }
        directory.delete();
    }
}