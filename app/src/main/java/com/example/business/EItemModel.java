package com.example.business;

public class EItemModel {
    private int image;
    private String name, businessName, location, businessType, businessStage, investment, investor, goals;

    public EItemModel() {
    }

    public EItemModel(int image, String name, String businessName, String location, String businessType, String businessStage, String investment, String investor, String goals) {
        this.image = image;
        this.name = name;
        this.businessName = businessName;
        this.location = location;
        this.businessType = businessType;
        this.businessStage = businessStage;
        this.investment = investment;
        this.investor = investor;
        this.goals = goals;

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
    public String getBusinessName() {
        return businessName;
    }
    public String getLocation() {
        return location;
    }
    public String getBusinessType() {
        return businessType;
    }
    public String getBusinessStage() {
        return businessStage;
    }
    public String getInvestment() {
        return investment;
    }
    public String getInvestor() {
        return investor;
    }
    public String getGoals() {
        return goals;
    }



}
