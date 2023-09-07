import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Blob
{
    //read file
    //get sha of contents
    //write new file to objects folder with sha name
    public String read (String fileName) throws IOException
    {
        String contents = "";
        BufferedReader reader = new BufferedReader(new FileReader (fileName));
        while (reader.ready())
        {
            contents += (char)reader.read();
        }
        reader.close();
        return contents;
    }

    public String encryptPassword(String password)
{
    String sha1 = "";
    try
    {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        sha1 = byteToHex(crypt.digest());
    }
    catch(NoSuchAlgorithmException e)
    {
        e.printStackTrace();
    }
    catch(UnsupportedEncodingException e)
    {
        e.printStackTrace();
    }
    return sha1;
}

    public String byteToHex(final byte[] hash)
{
    Formatter formatter = new Formatter();
    for (byte b : hash)
    {
        formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
}
}
//objects folder gets all of the files
//index makes a list of original file names that were added to objects folder and the new name that is in the objects folder