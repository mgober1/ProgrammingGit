import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExampleTester {

    private String file1Name = "test.txt";
    private String sha = "17ed26b0ccf83747502dfc6d7b3ee77da6ee4569";

    @BeforeAll
    public static void createTestFiles() {
        FileEditor.createFile("test.txt");
        FileEditor.writeFile("test.txt", "Hello0000 !");
    }

    @AfterAll
    public static void deleteTestFiles() {
        FileEditor.deleteFile("test.txt");
    }

    @Test
    @DisplayName("[1] Test if blob is created correctly")
    public void testCreateBlob() throws IOException {
        Blob blob = new Blob();
        String contents = blob.read("test.txt");
        blob.encryptPassword(contents);
        blob.write();
        assertEquals("Hello0000 !", contents);
        File file = new File("objects/" + sha);
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("[2] Test if index is working correctly")
    public void testIndex() throws IOException {
        FileEditor.deleteDirectory("objects");
        FileEditor.deleteFile("index");

        Index index = new Index();
        index.initialize();
        File indexFile = new File("index");
        File objectsFolder = new File("objects");

        assertTrue(indexFile.exists());
        assertTrue(objectsFolder.exists());

        index.addBlob("test.txt");
        File blobFile = new File("objects/" + sha);

        assertTrue(blobFile.exists());
        assertEquals(FileEditor.readFile("index"), file1Name + " : " + sha);
    }

}