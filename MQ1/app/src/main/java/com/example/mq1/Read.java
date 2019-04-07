package com.example.mq1;

public class Read {
    public String title;
    public String name;
    public String image;
    public String avatar;
    public String discript;

    public Read(String title, String name, String image, String avatar) {
        this.title = title;
        this.name = name;
        this.image = image;
        this.avatar = avatar;
    }

    public String getDiscript() {
        return discript;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

   // public Read(String name, String image, String title){
    //    this.title = title;
     //   this.name = name;
      //  this.image = image;
   // }
    public Read(){

    }
    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
