package com.mmil.abhi.quizzard.model;

/**
 * Created by abhi on 8/4/18.
 */

public class Category {
  private String Name;
  private String Image;

  public Category(String name, String image) {
    Name = name;
    Image = image;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    this.Name = name;
  }

  public String getImage() {
    return Image;
  }

  public void setImage(String image) {
    this.Image = image;
  }
}
