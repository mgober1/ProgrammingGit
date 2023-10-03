public class TempTester
{
    public static void main (String [] args) throws Exception
    {
        Tree tree = new Tree();
        /*File testFolder = new File("testfolder");
        tree.traverseDir(testFolder);*/
        tree.addDirectory("testfolder");
    }
}
