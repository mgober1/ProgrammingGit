import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Index {
    File index;
    File folder;

    // public void initialize(String fileName, String folderName)
    public void initialize() throws IOException {
        index = new File("index");
        if (!index.exists()) {
            index.createNewFile();
        }
        folder = new File("objects");
        folder.mkdir();
    }

    public void addBlob(String fileName) throws IOException {
        Blob blob = new Blob();
        String contents = blob.read(fileName);
        String sha = blob.encryptPassword(contents);
        blob.write();
        String indexContents = "";
        BufferedReader reader = new BufferedReader(new FileReader(index));
        while (reader.ready()) {
            indexContents += (char) reader.read();
        }
        reader.close();
        PrintWriter writer = new PrintWriter(index);
        writer.print(indexContents);
        writer.println(fileName + " : " + sha);
        writer.close();
    }

    public void removeBlob(String fileName) throws IOException {
        Blob blob = new Blob();
        String contents = blob.read(fileName);
        String sha = blob.encryptPassword(contents);

        // dont want to delete the actual file in the objects folder yet
        //
        // File file = new File(fileName);
        // File folder = new File("objects");
        // File[] files = folder.listFiles();
        // folder.mkdir();
        // File shaFile = new File(sha);
        // for (File f : files) {
        // if (f.getName().equals(shaFile.getName())) {
        // f.delete();
        // }
        // }
        // file.delete();

        File inputFile = new File("index");
        File tempFile = new File("myTempFile");
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = fileName + " : " + sha;
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove))
                continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        // renaming the file breaks it
        //
        // tempFile.renameTo(inputFile);

        String tempFileContents = "";
        BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
        while (tempReader.ready()) {
            tempFileContents += (char) tempReader.read();
        }
        tempReader.close();
        PrintWriter tempWriter = new PrintWriter(index);
        tempWriter.print(tempFileContents);
        tempWriter.close();
    }
}