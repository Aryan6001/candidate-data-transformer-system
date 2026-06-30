package com.eightfold.transformer.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class FileUtil {

    private FileUtil() {
    }

    public static void createDirectory(String directory) {

        File dir = new File(directory);

        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static boolean exists(String path) {
        return new File(path).exists();
    }

    public static String readFile(String path) throws IOException {
        return Files.readString(new File(path).toPath());
    }
}