import java.io.File;

public class TempTester
{
    public static void main (String [] args)
    {
        Tree tree = new Tree();
        File testFolder = new File("testFolder");
        tree.traverseDir(testFolder);
    }
}
