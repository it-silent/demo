package com.hy.demo.lean;

import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.util.Scanner;

/**
 * FileTest
 *
 * @author silent
 * @date 2018/4/19
 */
public class FileTest {

    public static void main(String[] args) throws Exception {
        String pathSeparator = File.pathSeparator;
//        System.err.println(File.separator);

//        File file = new File("/Users/silent/Documents/file");
//        System.err.println(String.format("is file : %s", file.isFile()));
//        System.err.println(String.format("is directory : %s", file.isDirectory()));
//        System.err.println(String.format("can read : %s", file.canRead()));
//        System.err.println(String.format("can wirte : %s", file.canWrite()));
//        System.err.println(String.format("parent : %s", file.getParent()));
//        System.err.println(String.format("length : %s", file.length()));
//
//        System.err.println("---------------------");
//        File documents = new File(file.getParent());
//        fileList(documents);

//        File deskTop = new File("/Users/silent/Desktop");
//        new File(deskTop, "file1.md").createNewFile();
//        new File(deskTop, "image/abs.jpeg").createNewFile();
//        new File(deskTop, "image/abs").mkdir();
//
//        String ext = ".DS_Store";
//        String[] s = deskTop.list((dir, name) -> !name.endsWith(ext));
//        System.err.println(s);


//        String filePath = "/Users/silent/Documents/file";
//        String outPath = "/Users/silent/Desktop/outputStreamfile";
//        File file = new File("/Users/silent/Documents/file");
//        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
//        FileOutputStream fileOutputStream = new FileOutputStream(new File(outPath));
//        try {
//            byte[] bytes = new byte[1024];
//            int len;
//            while ((len = fileInputStream.read(bytes)) != -1) {
//                System.out.println(new String(bytes, 0, len));
//                System.err.println("===============");
//                fileOutputStream.write(bytes, 0, len);
//                fileOutputStream.flush();
//            }
//        } catch (Exception e) {
//
//        } finally {
//            fileInputStream.close();
//            fileOutputStream.close();
//        }

//        String filePath = "/Users/silent/Documents/file";
//        String outPath = "/Users/silent/Desktop/file";
//        FileReader fileReader = new FileReader(new File(filePath));
//        FileWriter fileWriter = new FileWriter(new File(outPath));
//        try {
//            char[] chars = new char[1024];
//            int len;
//            while((len = fileReader.read(chars)) != -1) {
//                System.out.println(new String(chars, 0, len));
//                System.err.println("===========");
//                fileWriter.write(chars, 0, len);
//                fileWriter.flush();
//            }
//        } catch (Exception e) {
//
//        } finally {
//            fileReader.close();
//            fileWriter.close();
//        }

//        try (
//                Reader reader = new FileReader(new File("/Users/silent/Documents/file"));
//                Writer writer = new FileWriter(new File("/Users/silent/Desktop/wileWriter"));) {
//            int len;
//            char[] chars = new char[1024];
//            while ((len = reader.read(chars)) != -1) {
//                writer.write(chars, 0, len);
//            }
//
//            System.err.println("done");
//        } catch (Exception e) {
//            System.err.println(e);
//        }

        // copy pic

//        String srcPath = "/Users/silent/Documents/images/timg.jpeg";
//        String destPath = "/Users/silent/Desktop/timg1.jpeg";
//        try (
//                InputStream in = new FileInputStream(new File(srcPath));
//                OutputStream out = new FileOutputStream(new File(destPath));
//        ) {
//            int len;
//            byte[] bytes = new byte[1024];
//            while ((len = in.read(bytes)) != -1) {
//                out.write(bytes, 0, len);
//            }
//            System.err.println("done");
//        } catch (Exception e) {
//            System.err.println(e);
//        }

        String srcPath = "/Users/silent/Documents/images/timg.jpeg";
        String destPath = "/Users/silent/Desktop/timg1.jpeg";
        try (
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(srcPath)));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(destPath)));
        ) {
            int len;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            System.err.println("done");
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    private static void fileList(File parentFile) {
        for (File file : parentFile.listFiles()) {
            if (file.isDirectory()) {
                fileList(file);
            }
            if (file.isFile()) {
                System.err.println(String.format("file parent : %s , file name : %s", file.getParent(), file.getName()));
            }
        }
    }

}
