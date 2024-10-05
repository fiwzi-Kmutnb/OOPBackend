package net.fiwzi.oop.internal.context.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;

public class Directory {
    private final Map<String, Directory> subdir;
    private final List<String> internal;

    public Directory(String name) {
        this.subdir = new HashMap<>();
        this.internal = new ArrayList<>();
    }

    public void addSubdirectory(String name, Directory directory) {
        subdir.put(name, directory);
    }

    public void addInternal(String fileName) {
        internal.add(fileName);
    }

    @JsonIgnore
    public Directory getSubdirectory(String name) {
        return subdir.get(name);
    }


    public Map<String, Directory> getSubdir() {
        return subdir;
    }

    public List<String> getInternal() {
        return internal;
    }

}
