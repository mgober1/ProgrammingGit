import java.io.IOException;

public class Tester 
{
    public static void main (String [] args) throws IOException
    {
        Blob blob = new Blob();
        String contents = blob.read("Test.txt");
        blob.encryptPassword(contents);
        blob.write();
    }
}
