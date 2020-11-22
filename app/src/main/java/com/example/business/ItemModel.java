package com.example.business;

public class ItemModel {
    private int image;
    private String name, location, experience, skills, investment, businessType;

    public ItemModel() {
    }

    public ItemModel(int image, String name, String location, String experience, String skills, String investment, String businessType) {
        this.image = image;
        this.name = name;
        this.location = location;
        this.experience = experience;
        this.skills = skills;
        this.investment = investment;
        this.businessType = businessType;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getExperience() {
        return experience;
    }

    public String getSkills() {
        return skills;
    }

    public String getInvestment() {
        return investment;
    }

    public String getBusinessType() {
        return businessType;
    }


}
