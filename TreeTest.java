import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Utilities.FileUtils;

public class TreeTest {
    private static String tree1 = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
    private static String blob1 = "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt";
    private static String blob2 = "blob : 81e0268c84067377a0advddb5cc996c93f6dcf9f : file2.txt";

    @BeforeAll
    static void createTestFiles() throws Exception {
        FileUtils.createDirectory("objects");
    }

    @AfterAll
    static void deleteTestFiles() throws Exception {
        FileUtils.deleteDirectory("objects");
    }

    @Test
    void testAdd() throws Exception {
        Tree tree = new Tree();
        tree.add(tree1);
        tree.add(blob1);
        tree.add(blob2);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add(tree1);
        expected.add(blob1);
        expected.add(blob2);

        assertEquals("Add() in Tree does not work", expected, tree.getLines());
    }

    @Test
    void testCreateBlob() throws Exception {
        Tree tree = new Tree();
        tree.add(tree1);
        tree.add(blob1);
        tree.add(blob2);
        tree.createBlob();
        assertTrue("CreateBlob() in Tree does not create the correct blob in the objects folder",
                FileUtils.fileExists("objects/" + FileUtils.sha1(tree1 + "\n" + blob1 + "\n" + blob2)));
    }

    @Test
    void testRemove() throws Exception {
        Tree tree = new Tree();
        tree.add(tree1);
        tree.add(blob1);
        tree.add(blob2);
        tree.remove("file1.txt");
        ArrayList<String> expected = new ArrayList<String>();
        expected.add(tree1);
        expected.add(blob2);
        assertEquals("Remove() in Tree does not work", expected, tree.getLines());
    }
}
