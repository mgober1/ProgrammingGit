import java.io.IOException;

public class Tester 
{
    public static void main (String [] args) throws IOException
    {
        //Blob blob = new Blob();
        //String contents = blob.read("Test.txt");
        //blob.encryptPassword(contents);
        //blob.write();
        Index index = new Index();
        index.initialize();
        index.addBlob("addBlobTest.txt");
        index.addBlob("addBlobTest2.txt");
        index.addBlob("addBlobTest3.txt");
        index.removeBlob("addBlobTest3.txt");
    }
}
