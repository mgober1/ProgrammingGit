import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Utilities.FileUtils;

public class IndexTest {
    protected static String file1Name = "test.txt";
    protected static String contents1 = "Hello0000 !";
    protected static String file2Name = "test2.txt";
    protected static String contents2 = "BiG FILE";

    @BeforeAll
    static void createTestFiles() throws Exception {
        FileUtils.createFile(file1Name);
        FileUtils.writeFile(file1Name, contents1);
        FileUtils.createFile(file2Name);
        FileUtils.writeFile(file2Name, contents2);
    }

    @AfterAll
    static void deleteTestFiles() throws Exception {
        FileUtils.deleteFile(file1Name);
        FileUtils.deleteFile(file2Name);
    }

    @Test
    void testInitialize() throws Exception {
        Index index = new Index();
        index.initialize();

        assertTrue("initialize() in Index does not create the index file", FileUtils.fileExists("index"));
        assertTrue("initialize() in Index does not create the objects folder", FileUtils.fileExists("objects"));
    }

    @Test
    void testAddBlob() throws Exception {
        Index index = new Index();
        index.initialize();

        index.addBlob(file1Name);
        index.addBlob(file2Name);

        assertTrue("addBlob() in Index does not create the blob file in the objects folder",
                FileUtils.fileExists("objects/" + FileUtils.sha1(contents1))
                        && FileUtils.fileExists("objects/" + FileUtils.sha1(contents2)));

        assertEquals("addBlob() in Index does not add the correct line to the index file",
                "test.txt : " + FileUtils.sha1(contents1) + "test2.txt : " +
                        FileUtils.sha1(contents2),
                FileUtils.readFile("index"));
        FileUtils.deleteFile("index");
    }

    @Test
    void testRemoveBlob() throws Exception {
        Index index = new Index();
        index.initialize();

        index.addBlob(file1Name);
        index.addBlob(file2Name);

        index.removeBlob(file1Name);

        assertEquals("removeBlob() in Index does not remove the correct line from the index file",
                "test2.txt : " + FileUtils.sha1(contents2),
                FileUtils.readFile("index"));
        FileUtils.deleteFile("index");
    }
}
