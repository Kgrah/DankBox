package com.example.lukecaughell.dankbox.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kyle_ on 8/12/2017.
 */

public class Router {

    public void moveToActivity (Context thisContext, Class<?> activity) {
        Intent intent = new Intent(thisContext, activity);
        thisContext.startActivity(intent);
    }
}
