import java.io.File;

public class Index 
{
    //public void initialize(String fileName, String folderName)
    public void initialize()
    {
        File index = new File("index");
        File folder = new File("objects");
        folder.mkdir();
    }

    
}