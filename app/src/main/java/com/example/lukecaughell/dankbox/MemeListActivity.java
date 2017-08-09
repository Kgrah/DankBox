package com.example.lukecaughell.dankbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemeListActivity extends MainActivity {

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
    }

    public void moveToImagePickerActivity() {
        Intent intent = new Intent(this, ColorCaptureActivity.class);
        startActivity(intent);
    }


}
