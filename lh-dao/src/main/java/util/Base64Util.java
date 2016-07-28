package util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * BASE64可逆算法加密器
 */
public class Base64Util {
    private Base64Util() {
    }

    public static String encrypt(Object source) {
        byte[] data;
        try {
            if (source instanceof File) {
                data = FileUtils.readFileToByteArray((File) source);
            } else if (source instanceof InputStream) {
                data = IOUtils.toByteArray((InputStream) source);
            } else if (source instanceof Reader) {
                data = IOUtils.toByteArray((Reader) source);
            } else if (source instanceof byte[]) {
                data = (byte[]) source;
            } else {
                data = source.toString().getBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    public static String decrypt(String encryptedText) {
        byte[] bytes = java.util.Base64.getDecoder().decode(encryptedText);
        return bytes == null ? null : new String(bytes);
    }

}
