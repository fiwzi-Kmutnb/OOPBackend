package net.fiwzi.oop.internal.context.interfaces;

import net.fiwzi.oop.utils.DirList;

import java.util.List;

public class DynamicObjectInterface {
    private DirList.DynamicObject file;
    private int countFind;

    public DirList.DynamicObject getFile() {
        return file;
    }

    public void setFile(DirList.DynamicObject a) {
        this.file = a;
    }

    public int getCountFind() {
        return countFind;
    }

    public void setCountFind(int b) {
        this.countFind = b;
    }
}
