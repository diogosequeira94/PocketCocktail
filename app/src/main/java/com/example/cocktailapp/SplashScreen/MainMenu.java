package com.example.cocktailapp.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cocktailapp.R;
import com.example.cocktailapp.activity.AnunciosActivity;
import com.example.cocktailapp.activity.CadastroActivity;
import com.example.cocktailapp.activity.Classroom;
import com.example.cocktailapp.activity.MeusAnunciosActivity;
import com.example.cocktailapp.activity.SobreActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.net.InetAddress;


public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();








        ImageView ima1 = findViewById(R.id.ima1);
        ImageView ima2 = findViewById(R.id.ima2);
        ImageView ima3 = findViewById(R.id.ima3);
        ImageView ima4 = findViewById(R.id.ima4);
        ImageView ima5 = findViewById(R.id.ima5);
        ImageView ima6 = findViewById(R.id.ima6);

        ima1.setOnClickListener(this);
        ima2.setOnClickListener(this);
        ima3.setOnClickListener(this);
        ima4.setOnClickListener(this);
        ima5.setOnClickListener(this);
        ima6.setOnClickListener(this);


    }


    //This method will check if the user has Internet Connectivity

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ima1:

                startActivity(new Intent(getApplicationContext(), AnunciosActivity.class));
                break;

                case R.id.ima2:


                // Check if user is logged in
              //  if(autenticacao.getCurrentUser() == null)


                //Checking if user has internet connection
                if (isOnline()){
                    startActivity(new Intent(getApplicationContext(), MeusAnunciosActivity.class));
                //If User is offiline send to another page
                }else {
                    Toast.makeText(getApplicationContext(), "Please enable network to access this feature", Toast.LENGTH_LONG).show();

                }



                break;
            case R.id.ima3:

                Toast.makeText(this, "Soon available!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ima4:

                startActivity(new Intent(getApplicationContext(), Classroom.class));
                break;
            case R.id.ima5:

                startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
                break;
            case R.id.ima6:

                startActivity(new Intent(getApplicationContext(), SobreActivity.class));
                break;
        }

    }
}
