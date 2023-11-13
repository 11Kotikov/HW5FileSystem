package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Tree {


    public static void main(String[] args) throws IOException {
        backupThisDirectory(".", "C:\\Users\\PC\\Desktop\\GeekBrains\\JavaSpecialization\\hw\\backup");
    }

    public static void backupThisDirectory(String sourceDir, String backupDir) throws IOException {
        if (sourceDir == null || backupDir == null)
            throw new IllegalArgumentException("Необходимо указать два пути: исходный и бекап");
        Files.createDirectories(Paths.get(backupDir));
        copyDirectoryToBackup(Paths.get(sourceDir), Paths.get(backupDir));
    }

    public static void copyDirectoryToBackup(Path sourceDir, Path backupDir) throws IOException {
        Files.walk(sourceDir)
                .forEach(file -> {
                    Path target = backupDir.resolve(sourceDir.relativize(file));
                    try {
                        Files.copy(file, target, REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}