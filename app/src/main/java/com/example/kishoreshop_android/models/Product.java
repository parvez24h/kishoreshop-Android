package com.example.kishoreshop_android.models;

public class Product {
    private int id;
    private String name;
    private String price;
    private String image;
    private String video;
    private String features;
    private int priority;
    private String created_at;
    private String updated_at;

    public Product() {
    }

    public Product(int id, String name, String price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product(int id, String name, String price, String image, String video, String features, int priority, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.video = video;
        this.features = features;
        this.priority = priority;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
