import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;

import Utilities.FileUtils;

public class Tree {

    protected ArrayList<String> lines;

    public Tree() {
        lines = new ArrayList<String>();
    }

    public void add(String newLine) throws Exception {
        lines.add(newLine);
    }

    public void remove(String lineToRemove) throws Exception {
        for (int i = lines.size() - 1; i >= 0; i--) {
            if (lines.get(i).contains(lineToRemove)) {
                lines.remove(i);
            }
        }
    }

    public void createBlob() throws Exception {
        Blob blob = new Blob();
        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            if (!content.toString().isEmpty()) {
                content.append("\n");
            }
            content.append(line);
        }
        blob.encryptPassword(content.toString());
        blob.setContents(content.toString());
        blob.write();
        FileUtils.deleteFile("tree");
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public String getSha1 (String password)
    {
            String sha1 = "";
            try {
                MessageDigest crypt = MessageDigest.getInstance("SHA-1");
                crypt.reset();
                crypt.update(password.getBytes("UTF-8"));
                sha1 = byteToHex(crypt.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return sha1;
        }

        public String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
