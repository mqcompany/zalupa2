package com.example.mq1;

public class IntoCard {
    private String Image;
    private String Discription;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public IntoCard(String image, String discription) {
        Image = image;
        Discription = discription;
    }
}
