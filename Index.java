import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Index 
{
    File index;
    File folder;
    //public void initialize(String fileName, String folderName)
    public void initialize()
    {
        index = new File("index");
        folder = new File("objects");
        folder.mkdir();
    }

    public void addBlob(String fileName) throws IOException
    {
        Blob blob = new Blob();
        String contents = blob.read(fileName);
        String sha = blob.encryptPassword(contents);
        blob.write();
        String indexContents = "";
        BufferedReader reader = new BufferedReader(new FileReader (index));
        while (reader.ready())
        {
            indexContents += (char) reader.read();
        }
        reader.close();
        PrintWriter writer = new PrintWriter (index);
        writer.print(indexContents);
        writer.println(fileName + " : " + sha);
        writer.close();
    }
    public void removeBlob(String fileName) throws IOException
    {
        Blob blob = new Blob();
        File file = new File(fileName);
        String contents = blob.read(fileName);
        String sha = blob.encryptPassword(contents);
        file.delete();
        /*BufferedReader reader = new BufferedReader(new FileReader (index));
        String fileLine = "";
        while (reader.ready())
        {
            if (fileLine.equals(fileName + " : " + sha))
            {

            }
            fileLine += reader.readLine();
        }
        reader.close();*/

        File inputFile = new File("index");
        File tempFile = new File("myTempFile");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = fileName + " : " + sha;
        String currentLine;

        while((currentLine = reader.readLine()) != null) 
        {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close(); 
        reader.close(); 
        boolean successful = tempFile.renameTo(inputFile);
    }
}