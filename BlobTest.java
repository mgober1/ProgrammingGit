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
        assertEquals("Blob encryptPassword() does not work", sha, FileUtils.sha1(password));
    }

    @Test
    void testRead() throws Exception {
        Blob blob = new Blob();
        String testContents = blob.read(fileName);
        assertEquals("Blob read() does not work", testContents, contents);
    }

    @Test
    void testWrite() throws Exception {
        Blob blob = new Blob();
        blob.read(fileName);
        blob.encryptPassword(contents);
        blob.write();

        assertTrue("Blob write() does not create the file",
                FileUtils.fileExists("objects/" + FileUtils.sha1(contents)));

        assertEquals("Blob write() does not work", contents, FileUtils.readFile("objects/" + FileUtils.sha1(contents)));

    }
}
