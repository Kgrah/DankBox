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
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;*/

import com.example.lukecaughell.dankbox.Classes.ImageData;
import com.example.lukecaughell.dankbox.Classes.MyAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String pathToImage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "doge_meme.jpg";

    String path = "sdcard/doge_meme.jpg";
    Uri file = Uri.fromFile(new File(path));
    private StorageReference mStorageRef;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ImageData> Images = prepareData();
        MyAdapter adapter = new MyAdapter(getApplication(), Images);
        recyclerView.setAdapter(adapter);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        Button memesListButton = (Button) findViewById(R.id.memes_list_button);
        memesListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                moveToMemeListActivity();
            }
        });

        Button uploadButton = (Button) findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Uri file = Uri.fromFile(new File(pathToImage));
                StorageReference riversRef = mStorageRef.child(pathToImage);

                riversRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
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

    public void moveToMemeListActivity () {
        Intent intent = new Intent(this, MemeListActivity.class);
        startActivity(intent);
    }

    public ImageData getImage() {
        return null;
    }

}
