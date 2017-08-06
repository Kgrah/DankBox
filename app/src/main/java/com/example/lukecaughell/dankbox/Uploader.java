package com.example.lukecaughell.dankbox;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by kyle_ on 8/5/2017.
 */

//public class Uploader {
//    String path;
//    StorageReference storageReference;
//    public Uploader (String path, StorageReference storageReference) {
//        this.path = path;
//        this.storageReference =  storageReference;
//    }
//
//    Uri file = Uri.fromFile(new File(path));
//    StorageReference riversRef = storageReference.child(path);
//
//    public void sendToFirebase() {
//        riversRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                //Get a URL to the uploaded content
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//            }
//        })
//        .addOnFailureListener(@NonNull Exception exception) {
//
//        }
//        }
//    }
//}
