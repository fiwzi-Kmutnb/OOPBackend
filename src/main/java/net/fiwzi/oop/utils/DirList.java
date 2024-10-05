package net.fiwzi.oop.utils;

import net.fiwzi.oop.infrastructure.Constant;
import net.fiwzi.oop.internal.context.interfaces.CountFindFileInterface;
import net.fiwzi.oop.internal.context.interfaces.Directory;
import net.fiwzi.oop.internal.context.interfaces.DynamicObjectInterface;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DirList {
    public static class DynamicObjectDir extends HashMap<String, Object> {}
    private Directory root = new Directory("root");
    public static int countDirectories(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            return 0;
        }
        return countDirectoriesRecursively(directory);
    }

    private static int countDirectoriesRecursively(File directory) {
        int count = 0;
        if (directory.getPath().split("/").length > 6) {
            count = 1;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    count += countDirectoriesRecursively(file);
                }
            }
        }
        return count;
    }
    private CountFindFileInterface listDirectories(String basePattern,int start) throws IOException {
        CountFindFileInterface file = new CountFindFileInterface();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(basePattern);
        List<String> directories = new ArrayList<>();
        String basePath = Constant.BASE_PATH;
        int i = 0;
        for (Resource resource : resources) {
            Path path = Paths.get(resource.getURI());
            if (Files.isDirectory(path)) {
                if(i >= start + 100)
                    break;
                String relativePath = path.toString().substring(basePath.length());
                if(relativePath.split("/").length >= 3)
                    i++;
                if(i <= start)
                    continue;
                directories.add(relativePath);
            }
        }
        file.setFile(directories);
        file.setCountFind(Math.max(i - start, 0));
        return file;
    }

    private void addPath(String[] paths) {
        Directory current = root;

        for (int i = 0; i < paths.length; i++) {
            String part = paths[i];

            if (i == paths.length - 1) {
                current.addInternal(part);
            } else {
                if (current.getSubdirectory(part) == null) {
                    current.addSubdirectory(part, new Directory(part));
                }
                current = current.getSubdirectory(part);
            }
        }
    }
    public DynamicObjectInterface DirGet(String basePattern, int start) throws IOException {
        if(start != 0)
            start = 100 * start;
        DynamicObjectInterface dynamicFileCount = new DynamicObjectInterface();
        CountFindFileInterface pathAll = this.listDirectories(basePattern, start);
        for (String pathName : pathAll.getFile()) {
            String[] pathSplit = pathName.split("/");
            int countf = countDirectories(Constant.BASE_PATH + pathName);
            if (countf == 1) {
                addPath(pathSplit);
            }
        }
        dynamicFileCount.setFile(root);
        dynamicFileCount.setCountFind(pathAll.getCountFind());
        return dynamicFileCount;
    }
}
