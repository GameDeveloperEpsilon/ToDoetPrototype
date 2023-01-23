package com.example.todoetprototype;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import com.google.gson.Gson;
import android.content.Intent;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.Attributes;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    private ImageView imageView;
    private Petspecies petspecies;


//    private ImageButton statusButton;
//    private ImageButton playButton;
//    private ImageButton optionsButton;
//    private ImageButton cleanButton;
//    private ImageButton healButton;
//    private ImageButton feedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtname = findViewById(R.id.textView2);
        //ImageView img = findViewById(R.id.imageaxo);
         ImageView imageaxo = (ImageView) findViewById(R.id.imageaxo);

        // randomized the petspecies

        Petspecies.species[] Speciesp = generateRandomEgg(3);
        for (Petspecies.species Species : Speciesp){
            System.out.println(Species);
        }



       // ImageView imageaxo = (ImageView) findViewById(R.id.imageaxo);
       // imageaxo.setImageResource(R.drawable.axoeggtest);



    }

    private Petspecies.species[] generateRandomEgg(int i) {
        return new Petspecies.species[0];
    }
}



