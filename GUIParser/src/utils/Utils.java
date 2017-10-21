package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import sun.misc.IOUtils;

/**
 *
 * @author ducanhnguyen
 */
public class Utils {

    /**
     * Doc noi dung file
     *
     * @param filePath duong dan tuyet doi file
     * @return noi dung file
     */
    public static String readFileContent(String filePath) {
        StringBuilder fileData = new StringBuilder(30000);
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filePath));
            char[] buf = new char[10];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return fileData.toString();
        }
    }

    /**
     * Tao folder
     *
     * @param path
     */
    public static void createFolder(String path) {
        File destDir = new File(path);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
    }

    public static void writeContentToFile(String content, String filePath) {
        try {
            createFolder(new File(filePath).getParent());
            PrintWriter out = new PrintWriter(filePath);
            out.println(content);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
