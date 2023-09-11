import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Tree {

    protected StringBuilder content;

    public Tree() {
        content = new StringBuilder();
    }

    public void add(String newLine) throws IOException {
        File file = new File("tree");
        if (!file.exists()) {
            file.createNewFile();
        }
        content.append(newLine);
        PrintWriter writer = new PrintWriter(file);
        writer.println(newLine);
        writer.close();
    }
}
