package com.example.cuoiia.model;

public class Story {
    private String name;
    private String content;

    public Story(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }
}
