import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Utilities.FileUtils;

public class BlobTest {
    protected static String password = "no wAY***@@!!";
    protected static String fileName = "test.txt";
    protected static String contents = "Hello0000 !";

    @BeforeAll
    static void createTestFiles() throws Exception {
        FileUtils.createFile(fileName);
        FileUtils.writeFile(fileName, contents);
    }

    @AfterAll
    static void deleteTestFiles() throws Exception {
        FileUtils.deleteFile(fileName);
    }

    @Test
    void testEncryptPassword() throws Exception {
        Blob blob = new Blob();
        String sha = blob.encryptPassword(password);

        // Tests that the sha1 hash is correct
        assertEquals("Blob encryptPassword() does not work", sha, FileUtils.sha1(password));
    }

    @Test
    void testRead() throws Exception {
        Blob blob = new Blob();
        String testContents = blob.read(fileName);

        // Tests that the contents of the file are correct
        assertEquals("Blob read() does not work", testContents, contents);
    }

    @Test
    void testWrite() throws Exception {
        Blob blob = new Blob();
        blob.read(fileName);
        blob.encryptPassword(contents);
        blob.write();

        // Tests that the blob is created in the objects folder
        assertTrue("Blob write() does not create the file",
                FileUtils.fileExists("objects/" + FileUtils.sha1(contents)));

        // Tests that the contents of the blob are correct
        assertEquals("Blob write() does not work", contents, FileUtils.readFile("objects/" + FileUtils.sha1(contents)));

    }
}
