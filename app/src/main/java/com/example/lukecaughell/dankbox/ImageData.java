package com.example.lukecaughell.dankbox;

/**
 * Created by kylelgrah on 8/3/17.
 */

public class ImageData {
    private String image_title;
    private Integer image_id;
    private String image_location;

    public String getImage_title() {
        return this.image_title;
    }

    public Integer getImage_id() {
        return this.image_id;
    }

    public void setImage_title(String android_version_name) {
        this.image_title = android_version_name;
    }

    public void setImage_id(Integer android_image_url) {
        this.image_id = android_image_url;
    }

    public void setImage_Location(String location) {
        this.image_location = location;
    }
}
