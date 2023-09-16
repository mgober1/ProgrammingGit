import java.io.IOException;
import java.net.URISyntaxException;
import Utilities.FileUtils;

public class Tester {
    public static void main(String[] args) throws Exception {
        // Blob blob = new Blob();
        // String contents = blob.read("Test.txt");
        // blob.encryptPassword(contents);
        // blob.write();
        Index index = new Index();
        index.initialize();
        index.addBlob("TestFiles/addBlobTest.txt");
        index.addBlob("TestFiles/addBlobTest1.txt");
        System.out.println(FileUtils.readFile("index"));
        // index.removeBlob("TestFiles/addBlobTest1.txt");
        // Tree tree = new Tree();
        // tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        // tree.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        // tree.add("blob : 81e0268c84067377a0advddb5cc996c93f6dcf9f : file2.txt");
        // tree.remove("file1.txt");
        // tree.remove("bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        // tree.createBlob();
        // System.out.println("DRU REED");

        // Tree tree = new Tree();
        // tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        // tree.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        // tree.add("blob : 883jd88c84dafjkh380jdnkjlcc996c93jdnbudk : file2.txt");
        // tree.createBlob();
        // System.out.println("HI");
    }
}
