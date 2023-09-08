import java.io.IOException;

public class Tester 
{
    public static void main (String [] args) throws IOException
    {
        Blob blob = new Blob();
        String contents = blob.read("Test.txt");
        System.out.println(blob.encryptPassword(contents));
        blob.write();
    }
}
