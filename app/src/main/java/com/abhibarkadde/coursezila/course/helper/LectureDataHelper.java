package com.abhibarkadde.coursezila.course.helper;

public class LectureDataHelper {
    String name, path;

    LectureDataHelper() {
    }

    public LectureDataHelper(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
