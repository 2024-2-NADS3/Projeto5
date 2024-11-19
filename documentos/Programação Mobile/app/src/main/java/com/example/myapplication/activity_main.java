package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import models.Usuario;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class activity_main extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String senha = password.getText().toString();

                RetrofitClient.apiService.loginUsuario(email, senha).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            Usuario usuarioLogado = response.body();
                            if (usuarioLogado != null) {
                                Toast.makeText(activity_main.this, "Bem-vindo, " + usuarioLogado.nome_completo + "!", Toast.LENGTH_SHORT).show();

                                // Redirecionar para a activity inicial.xml
                                Intent intent = new Intent(activity_main.this, inicial.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(activity_main.this, "Erro ao fazer login: Usuário não encontrado.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            try {
                                String errorResponse = response.errorBody().string();
                                Log.e("LoginErro", "Código de erro: " + response.code() + ", Resposta do servidor: " + errorResponse);
                                Toast.makeText(activity_main.this, "Erro ao fazer login: " + response.code(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                Log.e("LoginErro", "Erro ao ler o corpo da resposta: " + e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        if (t instanceof HttpException) {
                            HttpException httpException = (HttpException) t;
                            int code = httpException.code();
                            try {
                                String errorBody = httpException.response().errorBody().string();
                                Log.e("LoginErro", "Erro na requisição: " + code + " - " + errorBody);
                                Toast.makeText(activity_main.this, "Erro ao fazer login: " + code + " - " + errorBody, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                Log.e("LoginErro", "Erro ao ler o corpo da resposta: " + e.getMessage());
                                Toast.makeText(activity_main.this, "Erro ao fazer login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("LoginErro", "Erro na requisição: " + t.getMessage());
                            Toast.makeText(activity_main.this, "Erro ao fazer login: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_main.this, pgcadastro.class);
                startActivity(intent);
            }
        });
    }
}