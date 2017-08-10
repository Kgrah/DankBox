package com.example.lukecaughell.dankbox.Classes;

import android.net.Uri;

import com.example.lukecaughell.dankbox.R;
import com.google.firebase.storage.StorageReference;

/**
 * Created by kyle_ on 8/8/2017.
 */

public class Uploader {
    StorageReference currentRef;
    StorageReference currentImageRef;
    private final String imageUri = "drawable://" + R.drawable.doge_meme;

    public Uploader (StorageReference mStorageRef) {
        this.currentRef = mStorageRef.child("doge.jpg");
        this.currentImageRef = mStorageRef.child(imageUri);
        currentRef.getName().equals(currentImageRef.getName());
        currentRef.getPath().equals(currentImageRef.getPath());
    }

    public void upload() {
        currentRef.putFile(Uri.parse(imageUri));
    }
}
