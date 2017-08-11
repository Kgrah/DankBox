// Data flow:
    // connect to FireBase storage
    // for each image in FireBase storage
    //      download image
    //      add image to ArrayList
    //      send ArrayList to adapter
    // adapter { for each image in ArrayList
    //              display image }



package com.example.lukecaughell.dankbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;*/

import com.example.lukecaughell.dankbox.Classes.ImageData;
import com.example.lukecaughell.dankbox.Classes.ImageList;
import com.example.lukecaughell.dankbox.Classes.MyAdapter;
import com.example.lukecaughell.dankbox.Classes.Uploader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_GALLERY_REQUEST = 20;
    String pathToImage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "doge_meme.jpg";

    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageList images = new ImageList();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ImageData> Images = images.prepareData();
        MyAdapter adapter = new MyAdapter(getApplication(), Images);
        recyclerView.setAdapter(adapter);
    }

    public void onImageGalleryClicked(View v) {
      /*  // do some stuff
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK); // <-- String

        //Data location
        File pictureDirectory  = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        //convert to Uri

        Uri data = Uri.parse(pictureDirectoryPath);

        //set data and type
        photoPickerIntent.setDataAndType(data,"image/jpg" );

        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);*/

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            StorageReference filePathReference = mStorageRef.child("Photos")
                    .child(uri.getLastPathSegment());

            filePathReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(MainActivity.this,"Upload Done", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
