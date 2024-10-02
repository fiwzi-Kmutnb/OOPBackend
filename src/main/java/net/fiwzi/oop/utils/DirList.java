package net.fiwzi.oop.utils;

import net.fiwzi.oop.infrastructure.Constant;
import net.fiwzi.oop.internal.context.interfaces.CountFindFileInterface;
import net.fiwzi.oop.internal.context.interfaces.DynamicObjectInterface;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DirList {
    public static class DynamicObject extends HashMap<String, Object> {}
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
                if(i < start)
                    continue;
                directories.add(relativePath);
            }
        }
        file.setFile(directories);
        if(start != 0)
            start--;
        file.setCountFind(Math.max(i - start, 0));
        return file;
    }
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
    private void addPath(DynamicObject obj, List<String> path) {
        for (int i = 0; i < path.size() - 2; i++) {
            String current = path.get(i);
            obj.computeIfAbsent(current, k -> new DynamicObject());
            Object value = obj.get(current);
            if (value instanceof DynamicObject) {
                obj = (DynamicObject) value;
            } else {
                DynamicObject newObj = new DynamicObject();
                obj.put(current, newObj);
                obj = newObj;
            }
        }
        String fileName = path.get(path.size() - 1);
        String dirName = path.get(path.size() - 2);

        if (obj.get(dirName) == null) {
            obj.put(dirName, new ArrayList<String>());
        }
        if (obj.get(dirName) instanceof List) {
            List<String> files = (List<String>) obj.get(dirName);
            if (!files.contains(fileName)) {
                files.add(fileName);
            }
        }
    }
    public DynamicObjectInterface DirGet(String basePattern,int start) throws IOException {
        if(start != 0)
            start = 100 * start;
        DynamicObjectInterface dynamicFileCount = new DynamicObjectInterface();
        CountFindFileInterface pathAll = this.listDirectories(basePattern,start);
        DynamicObject root = new DynamicObject();
        for (String pathName : pathAll.getFile()) {
            String[] pathSplit = pathName.split("/");
            if(pathSplit.length < 2)
                continue;
            addPath(root,Arrays.asList(pathSplit));
        }
        dynamicFileCount.setFile(root);
        dynamicFileCount.setCountFind(pathAll.getCountFind());
        return dynamicFileCount;
    }
}