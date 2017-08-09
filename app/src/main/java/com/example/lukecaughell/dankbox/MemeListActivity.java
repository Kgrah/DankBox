package com.example.lukecaughell.dankbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class MemeListActivity extends MainActivity {
    private static final int IMAGE_GALLERY_REQUEST = 20;
    private StorageReference mStorageRef;
    StorageReference currentRef;

    private final String[] spiderManMemes = {
        "Hay", "Hay 2"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_list);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        currentRef = mStorageRef.child("images/island.jpg");


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
