package file;

import java.util.Base64;

public class FileSerializer {

    public static String serialize(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

    public static byte[] deserialize(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
