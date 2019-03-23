package com.example.cocktailapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.cocktailapp.R;
import com.example.cocktailapp.adapter.AdapterAnuncios;
import com.example.cocktailapp.helper.ConfiguracaoFirebase;
import com.example.cocktailapp.helper.RecyclerItemClickListener;
import com.example.cocktailapp.model.Anuncio;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MeusAnunciosActivity extends AppCompatActivity {

    private RecyclerView recyclerAnuncios;
    private List<Anuncio> anuncios = new ArrayList<>();
    private AdapterAnuncios adapterAnuncios;
    private DatabaseReference anuncioUsuarioRef;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_anuncios);



                setTitle("My Cocktails");


        //Initial Configs
        anuncioUsuarioRef = ConfiguracaoFirebase.getFireBase()
                .child("meus_anuncios")
                .child(ConfiguracaoFirebase.getIdUsuario());


        inicializarComponentes();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CadastrarAnuncio.class));

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //RecyclerView Configuration
        recyclerAnuncios.setLayoutManager(new LinearLayoutManager(this));
        recyclerAnuncios.setHasFixedSize(true);
        adapterAnuncios = new AdapterAnuncios(anuncios,this);
        recyclerAnuncios.setAdapter(adapterAnuncios);

        //Recovers cocktails to user
        recuperarAnuncios();

        //Dialog



        //Adds onClick to the recyclerView
        recyclerAnuncios.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this, recyclerAnuncios, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Getting selected cocktail to edit

                        Anuncio anuncioSelecionado = anuncios.get(position);
                        Intent i = new Intent(MeusAnunciosActivity.this, DetalhesProdutoActivity.class);
                        i.putExtra("anuncioSelecionado", anuncioSelecionado);
                        startActivity(i);


                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {

                        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(MeusAnunciosActivity.this);

                        //Alert Dialog Configuration
                        dialog.setTitle("Confirmation");
                        dialog.setMessage("Are you sure you want to delete this cocktail?");
                        dialog.setCancelable(false);

                        dialog.setIcon(R.drawable.icon);

                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Anuncio anuncioSelecionado = anuncios.get(position);
                                anuncioSelecionado.remover();

                                adapterAnuncios.notifyDataSetChanged();

                                Toast.makeText(MeusAnunciosActivity.this, "Cocktail deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MeusAnunciosActivity.this, "Action Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.create();
                        dialog.show();

                    }


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
                )
        );

    }


    private void recuperarAnuncios(){

        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Loading Cocktails")
                .setCancelable(false)
                .build();
        dialog.show();


        anuncioUsuarioRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            anuncios.clear();
            for (DataSnapshot ds : dataSnapshot.getChildren()){
                anuncios.add(ds.getValue(Anuncio.class));
            }

            Collections.reverse(anuncios);
            adapterAnuncios.notifyDataSetChanged();

            dialog.dismiss();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }


    public void inicializarComponentes(){


        recyclerAnuncios = findViewById(R.id.recyclerAnuncios);

    }

}
