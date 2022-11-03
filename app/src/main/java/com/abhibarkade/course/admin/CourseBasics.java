package com.abhibarkade.course.admin;

import java.util.UUID;

public class CourseBasics {
    String title, subtitle, description, creator, language, thumbnailUrl, id;

    public CourseBasics() {
    }

    public CourseBasics(String title, String subtitle, String description, String creator, String language, String id) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.creator = creator;
        this.language = language;
        this.id = id;
    }

    public CourseBasics(String title, String subtitle, String description, String creator, String language, String thumbnailUrl, String id) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.creator = creator;
        this.language = language;
        this.thumbnailUrl = thumbnailUrl;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getthumbnailUrl() {
        return thumbnailUrl;
    }

    public void setthumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "CourseBasics{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", creator='" + creator + '\'' +
                ", language='" + language + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
