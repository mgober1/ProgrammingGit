import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    public void removeBlob(String fileName)
    {

    }
}