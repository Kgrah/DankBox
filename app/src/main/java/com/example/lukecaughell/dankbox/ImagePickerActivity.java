package com.example.lukecaughell.dankbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

/**
 * Created by kyle_ on 8/7/2017.
 */

public class ImagePickerActivity extends AppCompatActivity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    /*@Override
    public int getCurrentMenuId() {return R.id.;}*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);
    }

    public void onImageGalleryClicked(View v) {
        // do some stuff
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK); // <-- String

        //Data location
        File pictureDirectory  = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        //convert to Uri

        Uri data = Uri.parse(pictureDirectoryPath);

        //set data and type
        photoPickerIntent.setDataAndType(data,"image/jpg" );

        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }
}
