package com.example.lukecaughell.dankbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4"
    };

    private final Integer image_ids[] = {
            R.drawable.doge,
            R.drawable.nerf,
            R.drawable.spiderman,
            R.drawable.yodawg
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ImageData> ImageDatas = prepareData();
        MyAdapter adapter = new MyAdapter(getApplication(), ImageDatas);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<ImageData> prepareData() {
        ArrayList<ImageData> theImage = new ArrayList<>();
        for (int i = 0; i < image_titles.length; i++) {
            ImageData imageData = new ImageData();
            imageData.setImage_title(image_titles[i]);
            imageData.setImage_id(image_ids[i]);
            theImage.add(imageData);
        }
        return theImage;
    }
}
