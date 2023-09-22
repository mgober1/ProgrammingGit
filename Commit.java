public class Commit
{
    private String author;
    private String sum;
    private String opt;



    public void Commit (String author, String summary, String optionalSha) throws Exception
    {
        this.author = author;
        sum = summary;
        opt = optionalSha;
        createTree ();
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

    public void createTree () throws Exception
    {
        Tree tree = new Tree ();
        tree.createBlob();

    }
}