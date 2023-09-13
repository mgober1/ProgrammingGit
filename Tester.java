import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        // Blob blob = new Blob();
        // String contents = blob.read("Test.txt");
        // blob.encryptPassword(contents);
        // blob.write();
        // Index index = new Index();
        // index.initialize();
        // index.addBlob("TestFiles/addBlobTest.txt");
        // index.addBlob("TestFiles/addBlobTest1.txt");
        // index.addBlob("addBlobTest3.txt");
        // index.removeBlob("addBlobTest2.txt");
        Tree tree = new Tree();
        tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        tree.add("blob : 81e0268c84067377a0advddb5cc996c93f6dcf9f : file2.txt");
        // tree.remove("file1.txt");
        // tree.remove("bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.createBlob();
    }
}
