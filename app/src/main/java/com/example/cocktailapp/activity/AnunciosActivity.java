package com.example.cocktailapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.cocktailapp.NewRecycler.ExampleAdapter;
import com.example.cocktailapp.NewRecycler.ExampleItem;
import com.example.cocktailapp.NewRecycler.RecyclerMain;
import com.example.cocktailapp.R;
import com.example.cocktailapp.helper.ConfiguracaoFirebase;
import com.example.cocktailapp.helper.RecyclerItemClickListener;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnunciosActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.buttonTap), "Hello there!", "Search for your favourite cocktails in this screen or add your own creations on the top right corner!")
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




   public void inicia(){

       //Creating a new arraylist for the new cocktail Cards

       final ArrayList<ExampleItem> exampleList = new ArrayList<>();
       exampleList.add(new ExampleItem(R.mipmap.daiquiri, "Classic Daiquiri","Rum", getString(R.string.daiqiri_desc), "- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.margarita, "Classic Margarita","Tequila", getString(R.string.margarita_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.mojito, "Mojito","Rum", getString(R.string.mojito_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.tequi, "Tequila Sunrise","Tequila", getString(R.string.tequi_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.porn, "Pornstar Martini","Vodka", "","- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.presid, "El Presidente","Rum", getString(R.string.elpre_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.english, "English Garden","Gin", "","- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.pina, "Pinacolada","Rum", getString(R.string.pina_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.sour, "Whisky Sour","Whisky", getString(R.string.sourw_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.cosmo, "Cosmopolitan","Vodka", getString(R.string.cosmo_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.negroni, "Caipirinha","Cachaca", getString(R.string.caipirina_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.negroni, "Caipiroska","Vodka", getString(R.string.roska_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.negroni, "Long Island","Mixed", getString(R.string.long_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.amaretto, "Amaretto Sour","Amaretto", getString(R.string.amaretto_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.ambassa, "Ambassador","Rum", "","- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.americano, "Americano","Rum", getString(R.string.americano_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.appletini, "Appletini","Rum", getString(R.string.appletini_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.aviation, "Aviation","Gin", getString(R.string.aviation_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.bacardi, "Bacardi Cocktail","Rum", getString(R.string.bacardi_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.baybree, "Bay Bree","Rum", getString(R.string.bay_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.bees, "Bees Knees","Gin", getString(R.string.bee_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.blackruss, "Black Russian","Vodka", getString(R.string.russian_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.blueberry, "Blueberry Tea","Amaretto", getString(R.string.blue_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.bolo, "Bolo Cocktail","Rum", getString(R.string.bolo_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.chocoti, "Chocolate Espresso M","Vodka", getString(R.string.choco_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.clovers, "Clovers Club","Gin", getString(R.string.clover_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.cuba, "Cuba Livre","Rum", getString(R.string.cuba_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));
       exampleList.add(new ExampleItem(R.mipmap.dark, "Dark and Stormy","Rum", getString(R.string.dark_desc),"- 50ml White Rum\n- 25ml Lime Juice\n- 50ml Sugar Syrup\n\nGarnish: One lime Wheel"));






       //Sorting Array Alphabetically

        Collections.sort(exampleList, new Comparator<ExampleItem>() {
            @Override
            public int compare(ExampleItem exampleItem, ExampleItem t1) {
                return exampleItem.getText1().compareTo(t1.getText1());
            }
        });





       mRecyclerView = findViewById(R.id.recyclerView);
       mRecyclerView.setHasFixedSize(true);
       mLayoutManager = new LinearLayoutManager(this);
       mAdapter = new ExampleAdapter(exampleList);

       mRecyclerView.setLayoutManager(mLayoutManager);
       mRecyclerView.setAdapter(mAdapter);







       //Setting OnClickListenter to the RecyclerView


       mRecyclerView.addOnItemTouchListener(
               new RecyclerItemClickListener(
                       getApplicationContext(),
                       mRecyclerView,
                       new RecyclerItemClickListener.OnItemClickListener() {
                           @Override
                           public void onItemClick(View view, int position) {

                               ExampleItem exampleItem = exampleList.get(position);

                               Toast.makeText(
                                       getApplicationContext(), "Cocktail Selected: " +exampleItem.getText1(),
                                       Toast.LENGTH_SHORT
                               ).show();

                               //Intent to start new activity




                               Intent intent = new Intent(getApplicationContext(), RecyclerMain.class);

                               Bundle bundle = new Bundle();

                               bundle.putInt("cocktail_url", exampleItem.getmImageResource());
                               intent.putExtras(bundle);
                               intent.putExtra("cocktail_title", exampleItem.getText1());
                               intent.putExtra("cocktail_description", exampleItem.getmDescricao());
                               intent.putExtra("cocktail_receita", exampleItem.getmReceita());



                               startActivity(intent);





                           }



                           @Override
                           public void onLongItemClick(View view, int position) {



                           }

                           @Override
                           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                           }
                       }
               )

       );



   }

    }


