package com.example.cocktailapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.cocktailapp.NewRecycler.ExampleAdapter;
import com.example.cocktailapp.NewRecycler.ExampleItem;
import com.example.cocktailapp.R;
import com.example.cocktailapp.helper.ConfiguracaoFirebase;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AnunciosActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonRegiao, buttonCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.buttonTap), "Hello there!", "Search for you favourite cocktails in this screen or add your own creations on the top right corner!")
                        .tintTarget(false)
                        .outerCircleColor(R.color.colorAccent)
                        .titleTextSize(25));



        //inicializarComponentes();

        inicia();

        //Initial Configs
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //autenticacao.signOut();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(autenticacao.getCurrentUser() == null){//user is logged out
            menu.setGroupVisible(R.id.grupo_deslogado, true);

            //I also want to add individual item from another group here

        }else{//user is logged in
            menu.setGroupVisible(R.id.group_logado, true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_cadastrar :
                startActivity(new Intent(getApplicationContext(), CadastroActivity.class));

                break;

            case R.id.menu_sair:
                autenticacao.signOut();
                invalidateOptionsMenu();
                break;

            case R.id.menu_anuncios :
                startActivity(new Intent(getApplicationContext(), MeusAnunciosActivity.class ));

                break;

            case R.id.menu_about:
                startActivity(new Intent(getApplicationContext(), SobreActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

   /* public void inicializarComponentes(){

        recyclerAnunciosPublicos = findViewById(R.id.recyclerAnunciosPublicos);*/


   public void inicia(){

       //Creating a new arraylist for the new cocktail Cards

       ArrayList<ExampleItem> exampleList = new ArrayList<>();
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Classic Daiquiri","Rum"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Classic Margarita","Tequila"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Mojito","Rum"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Tequila Sunrise","Tequila"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Pornstar Martini","Vodka"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "El Presidente","Rum"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "English Garden","Gin"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Pinacolada","Rum"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Whisky Sour","Whisky"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Cosmopolitan","Vodka"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Caipirinha","Cachaca"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Caipiroska","Vodka"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Long Island","Mixed"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Manhattan","Rum"));
       exampleList.add(new ExampleItem(R.drawable.cocktail_ic, "Mint Julep","Rum"));




       mRecyclerView = findViewById(R.id.recyclerView);
       mRecyclerView.setHasFixedSize(true);
       mLayoutManager = new LinearLayoutManager(this);
       mAdapter = new ExampleAdapter(exampleList);

       mRecyclerView.setLayoutManager(mLayoutManager);
       mRecyclerView.setAdapter(mAdapter);



   }

    }


