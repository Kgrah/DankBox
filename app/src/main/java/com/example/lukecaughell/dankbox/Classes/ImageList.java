package com.example.lukecaughell.dankbox.Classes;

import com.example.lukecaughell.dankbox.R;

import java.util.ArrayList;

/**
 * Created by kyle_ on 8/9/2017.
 */
//contains images retrieved from the firebase storage databse by an ImageService
public class ImageList {
    private ArrayList<ImageData> images;

    private String[] image_retrieved_titles;
    private Integer[] image_retrieved_ids;

    private final String image_titles[] = {
            "Doge Memes",
            "Nerf Memes",
            "Spiderman Memes",
            "Yodawg Memes"
    };

    private final Integer image_ids[] = {
            R.drawable.doge,
            R.drawable.nerf,
            R.drawable.spiderman,
            R.drawable.yodawg
    };

    public ArrayList<ImageData> prepareData() {
        ArrayList<ImageData> theImage = new ArrayList<>();
        for (int i = 0; i < image_titles.length; i++) {
            ImageData imageData = new ImageData();
            imageData.setImage_title(image_titles[i]);
            imageData.setImage_id(image_ids[i]);
            theImage.add(imageData);
        }
        return theImage;
    }

    public String[] retriveImageTitles () {
        return null;
    }

    public String[] retrieveImages () {
        return null;
    }
}
