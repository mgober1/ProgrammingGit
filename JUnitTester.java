import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Utilities.FileUtils;

public class JUnitTester {

    private static String file1Name = "test.txt";
    private static String file1Content = "Hello0000 !";
    private String sha = "17ed26b0ccf83747502dfc6d7b3ee77da6ee4569";
    private static String file2Name = "test2.txt";
    private static String file2Content = "BiG FILE";
    private String sha2 = "600888109412bf4640b08ef4bf210101f5686dec";
    private String tree1 = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
    private String blob1 = "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt";
    private String blob2 = "blob : 81e0268c84067377a0advddb5cc996c93f6dcf9f : file2.txt";

    @BeforeAll
    public static void createTestFiles() throws Exception {
        FileUtils.createFile(file1Name);
        FileUtils.writeFile(file1Name, file1Content);
        FileUtils.createFile(file2Name);
        FileUtils.writeFile(file2Name, file2Content);
    }

    @AfterAll
    public static void deleteTestFiles() throws Exception {
        FileUtils.deleteFile(file1Name);
        FileUtils.deleteFile(file2Name);
    }

    @Test
    @DisplayName("[1] Test if blob is created correctly")
    public void testCreateBlob() throws Exception {
        Blob blob = new Blob();
        String contents = blob.read(file1Name);
        blob.encryptPassword(contents);
        blob.write();
        assertEquals(file1Content, contents);
        File file = new File("objects/" + sha);
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("[2] Test if index is working correctly")
    public void testIndex() throws Exception {
        FileUtils.deleteDirectory("objects");
        FileUtils.deleteFile("index");

        Index index = new Index();
        index.initialize();
        File indexFile = new File("index");
        File objectsFolder = new File("objects");

        assertTrue(indexFile.exists());
        assertTrue(objectsFolder.exists());

        index.addBlob(file1Name);
        File blobFile = new File("objects/" + sha);

        index.addBlob(file2Name);
        File blobFile2 = new File("objects/" + sha2);

        assertTrue(blobFile.exists() && blobFile2.exists());
        assertEquals(FileUtils.readFile("index"), file1Name + " : " + sha + file2Name + " : " + sha2);

        index.removeBlob(file1Name);
        assertEquals(FileUtils.readFile("index"), file2Name + " : " + sha2);
    }

    @Test
    @DisplayName("[3] Test if tree is working correctly")
    public void testTree() throws Exception {
        Tree tree = new Tree();
        tree.add(tree1);
        tree.add(blob1);
        tree.add(blob2);
        tree.createBlob();

        assertEquals(FileUtils.readFile("objects/1c6a0c6a2c15d7a1d65fba9b18dc71aec0f00892"),
                tree1 + blob1 + blob2);

        tree.remove("file1.txt");
        tree.remove("bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.createBlob();

        assertEquals(FileUtils.readFile("objects/9382eea02da161a06b49b6ca378a1c6122e7109e"), blob2);
    }
}