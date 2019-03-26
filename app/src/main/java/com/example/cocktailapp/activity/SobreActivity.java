package com.example.cocktailapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cocktailapp.R;

import mehdi.sakout.aboutpage.AboutPage;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        String descricao = "It’s in large part because of the way the industry is set up: Spirits companies sell liquor—on levels of “premium-ness”—but people want cocktail experiences. There are barriers at every step to achieving a good cocktail experience at home: from deciding what to make, to finding a good recipe, to buying what you need, to actually making the drink with confidence. As a result, making a cocktail at home is intimidating and difficult: it never tastes the same as it does when you’re out, it takes you away from your guests, and you never seem to have the right ingredients on hand.";

        View sobre = new AboutPage(this)
                .setImage(R.drawable.negroni)
                .setDescription(descricao)
                .addGroup("Contact me")
                .addEmail("diogosequeira@hotmail.fr", "Send me an Email!")
                .addWebsite("http://diogosequeira.epizy.com", "Acess my website!")
                .addGroup("Social Networks")
                .addFacebook("google", "Message me on Facebook!")
                .addTwitter("google", "Follow me on Twitter!")
                .addGitHub("diogosequeira94", "Check out my GitHub!")
                .addInstagram("wcocktailbible", "Follow me on Instagram")
                .create();

        setContentView( sobre );



    }
}
