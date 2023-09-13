import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tree {

    protected ArrayList<String> lines;

    public Tree() {
        lines = new ArrayList<String>();
    }

    public void add(String newLine) throws IOException {
        File file = new File("tree");
        if (!file.exists()) {
            file.createNewFile();
        }
        lines.add(newLine);
        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            content.append(line + "\n");
        }
        PrintWriter writer = new PrintWriter(file);
        writer.println(content);
        writer.close();
    }

    public void remove(String lineToRemove) throws IOException {
        File file = new File("tree");
        if (!file.exists()) {
            file.createNewFile();
        }
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).contains(lineToRemove)) {
                content.append(lines.get(i) + "\n");
            }
        }
        for (int i = lines.size() - 1; i >= 0; i--) {
            if (lines.get(i).contains(lineToRemove)) {
                lines.remove(i);
            }
        }

        PrintWriter writer = new PrintWriter(file);
        writer.println(content);
        writer.close();
    }
}
