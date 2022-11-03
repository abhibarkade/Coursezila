package com.abhibarkade.course.admin;

public class LectureDetails {
    String name, link;
    String length;

    public LectureDetails() {
    }

    public LectureDetails(String name, String link, String length) {
        this.name = name;
        this.link = link;
        this.length = length;
    }

    public LectureDetails(String name) {
        this.name = name;
    }

    public LectureDetails(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "LectureDetails{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", length=" + length +
                '}';
    }
}
