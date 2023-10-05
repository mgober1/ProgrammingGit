import java.io.File;
import java.io.FileWriter;

public class Commit
{
    private String author;
    private String sum;
    private String opt;
    /*1st line the SHA1 of a Tree object (never can be null)
2nd line is the SHA1 of a file location of the previous commit (can be blank / aka null)
3rd line is the SHA1 of a file location of the next commit (can be blank / aka null)
4th line is the author
5th line is the date
6th line is the Summary*/



    public void Commit (String author, String summary, String optionalSha) throws Exception
    {
        this.author = author;
        sum = summary;
        opt = optionalSha;
        File commit = new File ("commit");
        commit.createNewFile();
        FileWriter writer = new FileWriter ("commit");
        writer.append(createTree());
        writer.append(optionalSha);
        writer.append("");
        writer.append(author);
        writer.append("2023");//make create date string in java
        writer.append(summary);
        writer.close();
        Blob blob = new Blob();
        blob.encryptPassword(blob.read("commit"));
        blob.write();
        commit.delete();
    }

    public void Commit (String author, String summary) throws Exception
    {
        this.author = author;
        sum = summary;
        createTree ();
    }

    public String getDate ()
    {
        return "";
    }

    public String createTree () throws Exception
    {
        Tree tree = new Tree ();
        return tree.createBlob();
    }
}