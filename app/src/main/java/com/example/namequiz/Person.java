package com.example.namequiz;

public class Person {

    private String name;
    private Integer image;

    public Person(String name, Integer img){
        this.name = name;
        this.image = img;
    }

    public String getName() {
        return name;
    }

    public Integer getImage() {
        return image;
    }

}
