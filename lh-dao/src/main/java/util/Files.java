package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Files {
    public static void write(byte[] from, OutputStream to) {
        write(new ByteArrayInputStream(from), to);
    }

    public static void write(InputStream from, OutputStream to) {
        try {
            byte[] buf = new byte[1024];
            int flag = 0;
            while ((flag = from.read(buf)) != -1) {
                to.write(buf, 0, flag);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(from);
            close(to);
        }
    }

    public static FileOutputStream fileOutputStream(String name) {
        try {
            return new FileOutputStream(name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static byte[] toBytes(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            write(inputStream, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String toString(InputStream inputStream) {
        return new String(toBytes(inputStream));
    }

    public static String read(InputStream inputStream, String charset) {
        try {
            return new String(toBytes(inputStream), charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(BufferedReader reader) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String setFileName(String name) {
        String timeString = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        Random random = new Random();
        int num = random.nextInt(1000);
        int position = name.lastIndexOf(".");
        String suffixString = name.substring(position);
        name = timeString + num + suffixString;
        return name;
    }
}