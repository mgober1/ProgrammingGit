import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;

import Utilities.FileUtils;

public class Tree {

    protected ArrayList<String> lines;

    public Tree() {
        lines = new ArrayList<String>();
    }

    public void add(String newLine) throws Exception {
        lines.add(newLine);
    }

    public void remove(String lineToRemove) throws Exception {
        for (int i = lines.size() - 1; i >= 0; i--) {
            if (lines.get(i).contains(lineToRemove)) {
                lines.remove(i);
            }
        }
    }

    public void createBlob() throws Exception {
        Blob blob = new Blob();
        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            if (!content.toString().isEmpty()) {
                content.append("\n");
            }
            content.append(line);
        }
        blob.encryptPassword(content.toString());
        blob.setContents(content.toString());
        blob.write();
        FileUtils.deleteFile("tree");
    }
}
