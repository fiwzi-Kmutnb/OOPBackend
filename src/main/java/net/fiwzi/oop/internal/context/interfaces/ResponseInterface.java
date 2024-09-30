package net.fiwzi.oop.internal.context.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.fiwzi.oop.utils.DirList;

public class ResponseInterface {
    @JsonProperty("msg")
    private String message;

    @JsonProperty("data")
    private Data data;

    public static class Data {
        @JsonProperty("file")
        private DirList.DynamicObject file;

        @JsonProperty("fileCount")
        private int fileCount;
        @JsonProperty("findFileCount")
        private int findFileCount;

        public DirList.DynamicObject getFile() {
            return file;
        }

        public void setFile(DirList.DynamicObject file) {
            this.file = file;
        }

        public int getFileCount() {
            return fileCount;
        }

        public void setFileCount(int fileCount) {
            this.fileCount = fileCount;
        }
        public void setFindFileCount(int findFileCount) {
            this.findFileCount = findFileCount;
        }
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}