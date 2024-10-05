package net.fiwzi.oop.internal.context.interfaces;

import net.fiwzi.oop.utils.DirList;

import java.util.List;

public class DynamicObjectInterface {
    private Directory file;
    private int countFind;

    public Directory getFile() {
        return file;
    }

    public void setFile(Directory a) {
        this.file = a;
    }

    public int getCountFind() {
        return countFind;
    }

    public void setCountFind(int b) {
        this.countFind = b;
    }
}
