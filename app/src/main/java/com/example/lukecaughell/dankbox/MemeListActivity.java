package com.example.lukecaughell.dankbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MemeListActivity extends MainActivity {
    private StorageReference mStorageRef;
    StorageReference currentRef;

    private final String[] spiderManMemes = {
        "Hay", "Hay 2"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_list);

        Button pickerButton = (Button) findViewById(R.id.image_picker_button);
        pickerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                moveToImagePickerActivity();
            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference();

        currentRef = mStorageRef.child("images/island.jpg");


    }

    public void moveToImagePickerActivity() {
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivity(intent);
    }
}
