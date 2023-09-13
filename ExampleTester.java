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

    private static String file1Name = "test.txt";
    private static String file1Content = "Hello0000 !";
    private static String sha = "17ed26b0ccf83747502dfc6d7b3ee77da6ee4569";
    private static String file2Name = "test2.txt";
    private static String file2Content = "BiG FILE";
    private static String sha2 = "600888109412bf4640b08ef4bf210101f5686dec";

    @BeforeAll
    public static void createTestFiles() {
        FileEditor.createFile(file1Name);
        FileEditor.writeFile(file1Name, file1Content);
        FileEditor.createFile(file2Name);
        FileEditor.writeFile(file2Name, file2Content);
    }

    @AfterAll
    public static void deleteTestFiles() {
        FileEditor.deleteFile(file1Name);
        FileEditor.deleteFile(file2Name);
    }

    @Test
    @DisplayName("[1] Test if blob is created correctly")
    public void testCreateBlob() throws IOException {
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
    public void testIndex() throws IOException {
        FileEditor.deleteDirectory("objects");
        FileEditor.deleteFile("index");

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
        assertEquals(FileEditor.readFile("index"), file1Name + " : " + sha + file2Name + " : " + sha2);

        index.removeBlob(file1Name);
        assertEquals(FileEditor.readFile("index"), file2Name + " : " + sha2);
    }

}