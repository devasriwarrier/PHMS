package com.example.loginregistration;

public class FoodItem {
    private String mFoodName;
    private int mFoodImage;
    private int mFoodCal;

    public FoodItem(String FoodName, int FoodImage, int FoodCal) {
        mFoodName = FoodName;
        mFoodImage = FoodImage;
        mFoodCal = FoodCal;
    }
    public String getFoodName() {
        return mFoodName;
    }
    public int getFoodImage() {
        return mFoodImage;
    }

    public int getFoodCal() {
        return mFoodCal;
    }
}
