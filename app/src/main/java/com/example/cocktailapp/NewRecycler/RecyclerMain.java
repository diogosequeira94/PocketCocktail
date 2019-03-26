package com.example.cocktailapp.NewRecycler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailapp.R;

public class RecyclerMain extends AppCompatActivity {

    private static final String TAG = "RecyclerMain";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);
        Log.d(TAG, "onCreate: started");

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("cocktail_url") && getIntent().hasExtra("cocktail_title")){
            Log.d(TAG, "getIncomingIntent: found intent extras");

            Bundle bundle = getIntent().getExtras();
            int pic=bundle.getInt("cocktail_url");




            String imageName = getIntent().getStringExtra("cocktail_title");
            String descriptionName = getIntent().getStringExtra("cocktail_description");
            String receitaName = getIntent().getStringExtra("cocktail_receita");

            setImage(pic,imageName,descriptionName,receitaName);

        }
    }

    private void setImage(Integer pic, String imageName, String descriptionName, String receitaName){
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView title = findViewById(R.id.textCocktail);
        title.setText(imageName);



        ImageView cocktail = findViewById(R.id.imageCocktail);
        cocktail.setImageResource(pic);

        TextView description = findViewById(R.id.descriptionCocktail);
        description.setText(descriptionName);

        TextView receita = findViewById(R.id.recipeCocktail);
        receita.setText(receitaName);


    }


}
