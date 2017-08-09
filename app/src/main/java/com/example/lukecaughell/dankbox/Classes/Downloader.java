package com.example.lukecaughell.dankbox.Classes;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

/**
 * Created by kyle_ on 8/5/2017.
 */

public class Downloader {
    private StorageReference mainStorageRef;
    Activity currentActivity;

    public Downloader(Activity currentActivity) {
        this.currentActivity = currentActivity;
        mainStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void download (StorageReference currentRef) {
        try {
            File localFile = File.createTempFile("images", "jpg");
            currentRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener
                    <FileDownloadTask.TaskSnapshot>() {
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //handle errors
                }
            });
        }
        catch (IOException ex) {
            //Do something else
    }

    }
}
