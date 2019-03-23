package com.example.cocktailapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.cocktailapp.R;
import com.example.cocktailapp.helper.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private Button botaoAcessar;
    private Switch tipoAcesso;
    private EditText campoEmail, campoSenha;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializaComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if(!email.isEmpty()){
                    if (!senha.isEmpty()){

                        //Checks the Switch status
                        if(tipoAcesso.isChecked()){//Register

                            autenticacao.createUserWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(CadastroActivity.this,
                                                "Sucess registing new user!",
                                                Toast.LENGTH_SHORT).show();

                                        //Directs user to Main Screen

                                    }else{

                                        String erroExcecao = "";

                                        try{
                                            throw task.getException();
                                        }catch (FirebaseAuthWeakPasswordException e){
                                            erroExcecao = "Type a stronger password!";
                                        }catch (FirebaseAuthInvalidCredentialsException e){
                                            erroExcecao = "Please, type a valid e-mail";
                                        }catch (FirebaseAuthUserCollisionException e){
                                            erroExcecao = "This account already exists!";

                                        }catch (Exception e){
                                            erroExcecao = "registing user: " + e.getMessage();
                                                    e.printStackTrace();
                                        }

                                    }
                                }
                            });

                        }else{//Login

                            autenticacao.signInWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(CadastroActivity.this,
                                                "Login sucessful",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), AnunciosActivity.class));
                                    }else{
                                        Toast.makeText(CadastroActivity.this,
                                                "Error Login in: " + task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }

                    }else{
                        Toast.makeText(CadastroActivity.this,
                                "Fill the password!",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(CadastroActivity.this,
                            "Fill the E-mail!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializaComponentes(){
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        tipoAcesso = findViewById(R.id.switchAcesso);
        botaoAcessar = findViewById(R.id.buttonAcesso);

    }
}
