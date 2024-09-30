package net.fiwzi.oop.internal.context.interfaces;

import java.util.List;

public class CountFindFileInterface {
    private List<String> file;
    private int countFind;

    // Getters and setters
    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> a) {
        this.file = a;
    }

    public int getCountFind() {
        return countFind;
    }

    public void setCountFind(int b) {
        this.countFind = b;
    }
}
